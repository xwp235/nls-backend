package com.nlsapi.core.common.advice;

import cn.hutool.core.util.StrUtil;
import com.nlsapi.core.common.resp.JsonResp;
import com.nlsapi.core.common.utils.MessageUtil;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RespAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !(
                methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreRespSerializable.class) ||
                        (methodParameter.getMethod() !=null && methodParameter.getMethod().isAnnotationPresent(IgnoreRespSerializable.class))
                        || StrUtil.containsAny(methodParameter.getDeclaringClass().getName(),"WebMvcEndpointHandlerMapping")
        );
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest req, ServerHttpResponse res) {
        JsonResp r;
        if (body == null) {
            r = JsonResp.ok();
        } else if (body instanceof JsonResp jsonResult) {
            r = jsonResult;
        } else {
            if (body instanceof LinkedHashMap){
                // 如果返回值是500或者404
                Map<String,Object> errorResult = (Map<String,Object>)body;
                String statusKey = "status";
                String errorKey ="error";
                if (errorResult.containsKey(statusKey)&& errorResult.containsKey(errorKey)
                        && errorResult.containsKey("path")&&errorResult.containsKey("timestamp")){
                    int status = (int)errorResult.get(statusKey);
                    String msg = "";
                    if (status == 404) {
                        msg= MessageUtil.getMessage("resourceNotFound");
                    } else if (status == 500) {
                        msg = MessageUtil.getMessage("serverInternalError");
                    }
                    r = JsonResp.error(msg).setCode(status);
                } else {
                    r = JsonResp.error(MessageUtil.getMessage("resourceNotFound")).setCode(500);
                }
            } else {
                r = JsonResp.data(body);
            }
        }
        if (r.getData() == null) {
            r.remove("data");
        }
        return r;
    }

}
