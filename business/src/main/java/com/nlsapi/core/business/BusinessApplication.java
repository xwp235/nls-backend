package com.nlsapi.core.business;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.nlsapi.core.common",
        "com.nlsapi.core.business"
})
public class BusinessApplication {

    public static void main(String[] args) {
        // 加载自定义jvm参数
        loadConfiguration();
        // 启动项目
        SpringApplication.run(BusinessApplication.class, args);
    }

    private static void loadConfiguration() {
        // 加载相应的.env文件
        var envName = System.getProperty("spring.profiles.active", "dev");
        // 获取当前系统属性
        var currentEnv = System.getenv();
        var dotenv = Dotenv.configure()
                .filename(".env."+envName)
                .load();
        dotenv.entries().forEach(entry -> {
            if (!currentEnv.containsKey(entry.getKey())) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }

}
