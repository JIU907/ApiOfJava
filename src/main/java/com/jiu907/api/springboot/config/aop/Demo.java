package com.jiu907.api.springboot.config.aop;

import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description: 被织入的demo
 * @date: 2022/2/21
 */
@Component
public class Demo {
    public void doSomething(){
        System.out.println("==========");
    }
}
