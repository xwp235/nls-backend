package com.nlsapi.core.business.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    DefaultKaptcha defaultKaptcha() {
        var defaultKaptcha = new DefaultKaptcha();
        var properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "90");
        properties.setProperty("kaptcha.image.height", "28");
        properties.setProperty("kaptcha.textproducer.font.size", "20");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
        properties.setProperty("kaptcha.noise.color", "255,96,0");
//        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.setProperty("kaptcha.obscurificator.impl", KaptchaWaterRipple.class.getName());
//        properties.setProperty("kaptcha.background.impl", KaptchaNoBackground.class.getName());
        var config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean
    DefaultKaptcha webKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "90");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
//        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
//        properties.setProperty("kaptcha.obscurificator.impl", KaptchaWaterRipple.class.getName());
        var config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
