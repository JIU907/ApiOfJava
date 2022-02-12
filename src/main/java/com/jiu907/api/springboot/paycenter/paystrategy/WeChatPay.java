package com.jiu907.api.springboot.paycenter.paystrategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/5
 */
@Component
/**
 * 环境变量添加该参数：如果@Profile中的value等于以下参数则该Bean会被注入
 *                  反之则不会被注入
 * --spring.profiles.active=prd
 */
@Profile(value = "dev")
public class WeChatPay extends AbstractPayModel{
    @Override
    public void doProcessor(String payload) {

    }
}
