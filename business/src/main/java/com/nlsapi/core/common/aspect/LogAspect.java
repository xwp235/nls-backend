package com.nlsapi.core.common.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.SymbolConstants;
import com.nlsapi.core.common.utils.LogUtil;
import com.nlsapi.core.common.utils.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 切面执行顺序: Around > Before > After
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final ObjectMapper objectMapper;
    private final AccessLogService accessLogService;

    private static final String[] IGNORE_PATHS ={
            "/login",
            "/logout"
    };

    private final PathPatternParser pathPatternParser = new PathPatternParser();


    @Pointcut("execution(public * com.nlsapi.core..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object handler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var request = RequestUtil.getServletRequest();
        if (Objects.isNull(request)){
            return proceedingJoinPoint.proceed();
        }
        var servletPath = request.getServletPath();
        var protocol = request.getProtocol();
        var serverPort = request.getServerPort();
        var remotePort = request.getRemotePort();
        var isIgnored = false;
        for (var path : IGNORE_PATHS) {
            var matchResult = pathPatternParser.parse(path).matches(
                    PathContainer.parsePath(servletPath)
            );
            if (matchResult) {
                isIgnored = true;
                break;
            }
        }
        if (isIgnored){
            return proceedingJoinPoint.proceed();
        }
        var startTime = System.currentTimeMillis();

        //构建Spring表达式参数
        var variables =
                getAopMethodContext(proceedingJoinPoint);
        var parameters = "";
        if (!variables.isEmpty()){
            var elExpression = new StringBuilder("#{");
            var i = 0;
            for (var key : variables.keySet()) {
                if (i == 0) {
                    elExpression.append(SymbolConstants.SINGLE_QUOTE);
                } else {
                    elExpression.append("+',");
                }
                elExpression.append(key).append("='+#").append(key);
                i++;
            }
            elExpression.append(SymbolConstants.RIGHT_CURLY_BRACKET);
            // 解析Spring表达式的值
            try {
                parameters  = parseExpressionValue(
                        elExpression.toString(), variables);
            } catch (Exception e) {
                LogUtil.warn("Spring el expression parse request parameter failed at [{}]-{} !", request.getMethod(),request.getRequestURI());
            }
        }

        var log = new LogData();
        var ip = RequestUtil.getClientIP(request);
        var logId = MDC.get(AppConstants.LOG_ID);
        var isInnerIp = StrUtil.equalsAny(ip, "127.0.0.1","0:0:0:0:0:0:0:1","localhost");
        var saveIp = isInnerIp ? "127.0.0.1":ip;
        var headerNames = request.getHeaderNames();
        var headerVals = new HashMap<String,Object>();
        while(headerNames.hasMoreElements()){
            var name = headerNames.nextElement();
            var value = request.getHeader(name);
            headerVals.put(name,value);
        }
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var ua = UserAgentUtil.parse(userAgent);
        var device = ua.getPlatform().toString();
        log
                .setLogId(Long.parseLong(logId))
                .setIp(saveIp)
                .setDevice(device)
                .setProtocol(protocol)
                .setPath(servletPath)
                .setReferer(request.getHeader(HttpHeaders.REFERER))
                .setMethod(request.getMethod())
                .setUserAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .setCreateTime(ZonedDateTime.now())
                .setServerPort(serverPort)
                .setRemotePort(remotePort);
        if (!headerVals.isEmpty()){
            log.setHeaders(objectMapper.writeValueAsString(headerVals));
        }
        if (StrUtil.isNotBlank(parameters)){
            log.setParameters(parameters);
        } else {
            log.setParameters(String.valueOf(SymbolConstants.STRIKE));
        }
//        if (AuthUtil.isLogin()) {
//            log.setUsername(AuthUtil.getUsername());
//        } else {
//            log.setUsername("-");
//        }
        try {
            var obj = proceedingJoinPoint.proceed();
            log.setStatus(AppConstants.REQUEST_SUCCESS);
            return obj;
        } catch (Exception e) {
            log.setStatus(AppConstants.REQUEST_ERROR);
            try (var sw = new StringWriter();
                 var pw = new PrintWriter(sw)) {
                e.printStackTrace(pw);
                log.setFailReason(sw.toString());
            }
            throw e;
        } finally {
            var endTime = System.currentTimeMillis();
            var totalTime = endTime - startTime;
            if (totalTime > 1000) {
                totalTime /=1000;
                log.setResponseTime(totalTime + "s");
            } else {
                log.setResponseTime(totalTime + "ms");
            }
            accessLogService.save(log);
        }
    }

    /**
     * 获取切面中的方法上下文
     */
    private Map<String, Object> getAopMethodContext(ProceedingJoinPoint proceedingJoinPoint) {
        //获取方法签名，参数名和参数值
        var methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        var args = proceedingJoinPoint.getArgs();
        var params = methodSignature.getParameterNames();
        //构建Spring表达式参数
        var variables = new HashMap<String,Object>(args.length);
        for (var i = 0; i < args.length; i++) {
            variables.put(params[i], args[i]);
        }
        return variables;
    }

    /**
     * 解析并返回Spring表达式的值
     */
    private <T> T parseExpressionValue(String expr, Map<String, Object> variables) {
        var parser = new SpelExpressionParser();
        var context = new StandardEvaluationContext();
        variables.forEach((key,val) -> {
            if (isMultipartFileList(val)) {
                  var fileList = (List<MultipartFile>)val;
                  var fileStrList = CollUtil.<String>newArrayList();
                  if (CollUtil.isNotEmpty(fileList)) {
                      for (var multipartFile : fileList) {
                          fileStrList.add(multipartFile.getOriginalFilename());
                      }
                  }
                  context.setVariable(key,fileStrList);
            } else {
                context.setVariable(key,val);
            }
        });
        var expression = parser.parseExpression(expr, new TemplateParserContext());
        return expression.getValue(context, (Class<T>) Object.class);
    }

    private boolean isMultipartFileList(Object obj) {
        if (obj instanceof List<?> list) {
            for (var element : list) {
                if (!(element instanceof MultipartFile)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
