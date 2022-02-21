package com.jiu907.api.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description: Spring模块启动类
 * @date: 2022/1/4
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.jiu907.api")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
