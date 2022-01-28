package com.jiu907.api.springboot.paycenter.paystrategy;

import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/5
 */
@Component
public class WeChatPay extends AbstractPayModel{
    @Override
    public void doProcessor(String payload) {

    }
}
