package com.nlsapi.core.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class RequestUtil {

    /**
     * 获取请求上下文路径
     */
    public static String getContextPath() {
        String contextPath = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            contextPath = request.getContextPath();
        }
        return contextPath;
    }

    /**
     * 获取请求中的参数值
     */
    public static String getRequestParameter(String name) {
        String value = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            value = request.getParameter(name);
        }
        return value;
    }

    /**
     * 获取Servlet请求对象
     */
    public static HttpServletRequest getServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            return attributes.getRequest();
        }
        return null;
    }

    /**
     * 获取请求中的属性值
     */
    public static Object getRequestAttribute(String name) {
        Object value = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            HttpServletRequest request = attributes.getRequest();
            value = request.getAttribute(name);
        }
        return value;
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * 默认检测的Header:
     *
     * <pre>
     * 1、X-Forwarded-For
     * 2、X-Real-IP
     * 3、Proxy-Client-IP
     * 4、WL-Proxy-Client-IP
     * </pre>
     *
     * <p>
     * otherHeaderNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request          请求对象{@link HttpServletRequest}
     * @param otherHeaderNames 其他自定义头文件，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     */
    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (otherHeaderNames!=null && otherHeaderNames.length > 0) {
            headers = ArrayUtils.addAll(headers, otherHeaderNames);
        }

        return getClientIPByHeader(request, headers);
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * headerNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request     请求对象{@link HttpServletRequest}
     * @param headerNames 自定义头，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     */
    public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
        String ip;
        for (String header : headerNames) {
            ip = request.getHeader(header);
            if (isUnknown(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    private static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && StringUtils.indexOf(ip, ',') > 0) {
            var ips = StringUtil.splitTrim(ip, ',');
            for (final String subIp : ips) {
                if (isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     *
     * @param ip 被检测的字符串
     * @return 是否未知
     */
    private static boolean isUnknown(String ip) {
        return !StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip);
    }

}
