package com.jiu907.api.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LeiLiMin
 * @Description: 获取Bean
 * @date: 2022/1/28
 */
@Configuration
public class ApplicationContextConfig implements ApplicationContextAware {
    /**
     * 存储上下文环境
     */
    private static ApplicationContext ctx = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextConfig.ctx = applicationContext;
    }

    // 后续需要的Bean可以再次自定义
}
