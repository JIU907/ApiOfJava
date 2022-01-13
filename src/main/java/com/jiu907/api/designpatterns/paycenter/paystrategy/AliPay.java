package com.jiu907.api.designpatterns.paycenter.paystrategy;

import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description: 模拟具体支付行为
 * @date: 2022/1/5
 */
@Component
public class AliPay extends AbstractPayModel{

    @Override
    public void beforeProcessor() {
        System.out.println("Ali Pay 校验状态");
    }

    @Override
    public void afterProcessor() {
        System.out.println("Ali Pay 校验MQ的确认信息");
    }

    @Override
    public void doProcessor(String payload) {
        System.out.println("Ali pay ！！！");
    }
}
