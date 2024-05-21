package com.nlsapi.core.common.interceptor;

import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(AppConstants.LOG_ID, IdWorkerUtil.getIdStr());
        return true;
    }

}
