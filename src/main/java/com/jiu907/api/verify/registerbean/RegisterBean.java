package com.jiu907.api.verify.registerbean;

import com.jiu907.api.verify.bi.SensorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/21
 */
@Component
public class RegisterBean {

    /**
     * 以@Bean的形式将神策服务加入到Spring上下文中
     */
    @Bean
    public SensorService configuration(@Value("#{spring.application.name}")String fileName){
        return new SensorService(fileName);
    }
}
