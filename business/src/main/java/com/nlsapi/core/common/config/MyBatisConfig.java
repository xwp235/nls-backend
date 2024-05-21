package com.nlsapi.core.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.nlsapi.core.business.mapper")
public class MyBatisConfig {
}
