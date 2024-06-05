package com.nlsapi.core.common.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nlsapi.core.business.interceptor.WebLoginInterceptor;
import com.nlsapi.core.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    com.fasterxml.jackson.databind.Module simpleModule() {
        var simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return simpleModule;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**").order(1);
        // 路径不要包含context-path
        registry.addInterceptor(new WebLoginInterceptor())
                .addPathPatterns("/web/**")
                // 不需要登录认证的url
                .excludePathPatterns(
                        "/web/kaptcha/image-code/*",
                        "/web/member/login",
                        "/web/member/register",
                        "/web/member/reset",
                        "/web/sms-code/send-for-register",
                        "/web/sms-code/send-for-reset"
                ).order(2);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 所有api接口都应用此配置
        registry.addMapping("/**")
                // 允许所有来源
                .allowedOriginPatterns(CorsConfiguration.ALL)
                // 允许所有header
                .allowedHeaders(CorsConfiguration.ALL)
                // 允许所有请求方式(GET,POST,...)
                .allowedMethods(CorsConfiguration.ALL)
                // 允许请求带上cookie
                .allowCredentials(true)
                // 一小时内不再需要预检（发送OPTIONS请求）
                .maxAge(3600);
    }

}
