package com.jiu907.api.springboot.controller;

import com.jiu907.api.springboot.config.aop.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LeiLiMin
 * @Description: 权限Controller
 * @date: 2022/1/5
 */
@RestController
@RequestMapping("/v1/authController")
public class AuthController {

    @Autowired
    private Demo testA;
    /**
     * 设计一个登录服务
     */
    @GetMapping
    public  void testAop(){
        testA.doSomething();
    }
}
