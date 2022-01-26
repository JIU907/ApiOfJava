package com.jiu907.api.designpatterns.paycenter;

import com.jiu907.api.designpatterns.paycenter.paystrategy.AbstractPayModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * @Author LeiLiMin
 * @Description: 支付中心设计模式构思
 * @date: 2022/1/4
 */
@SpringBootApplication
public class PayCenter implements ApplicationContextAware {

    // 存储Spring上下文
    private static ApplicationContext ctx = null;

    public static void main(String[] args) {
        SpringApplication.run(PayCenter.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PayCenter.ctx = applicationContext;
    }


    /**
     * 将所有的PayModel封装为<beanName,Bean>的Map
     */
    @Bean
    Map<String,AbstractPayModel> producerPayModel(){
        Map<String, AbstractPayModel> beansOfType = PayCenter.ctx.getBeansOfType(AbstractPayModel.class);
        return beansOfType;
    }

    public AbstractPayModel getPayBeanByName(String beanName){
        AbstractPayModel bean =(AbstractPayModel)PayCenter.ctx.getBean(beanName);
        return bean;
    }
}
