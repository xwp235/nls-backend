package com.nlsapi.core.common.aspect;

import com.nlsapi.core.common.utils.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面执行顺序: Around > Before > After
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.nlsapi.core.*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        LogUtil.info("前置通知:{}", 1);
    }

    @Around("controllerPointcut()")
    public Object handler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var obj = proceedingJoinPoint.proceed();
        return obj;
    }

    @After("controllerPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        LogUtil.info("后置通知:{}", 1);
    }

}
