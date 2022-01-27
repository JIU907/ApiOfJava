package com.jiu907.api.designpatterns.paycenter;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/27
 */
@Component
// 是否需要注入容器
@Profile("prd")
// Bean的作用域
@Scope("prototype")
public class TestProfile {
    /**
     * 环境变量添加该参数：如果@Profile中的value等于以下参数则该Bean会被注入
     *                  反之则不会被注入
     * --spring.profiles.active=prd
     */
}
