package com.jiu907.api.springboot.config;

import com.jiu907.api.verify.sensorsbi.SensorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description: 注册Bean
 * @date: 2022/1/30
 */
@Configuration
public class RegisterBeanConfig {
    /**
     * 以@Bean的形式将神策服务加入到Spring上下文中
     */
    @Bean
    public SensorService configuration(@Value("${spring.application.name}")String fileName){
        return new SensorService(fileName);
    }
}
