package com.jiu907.api.designpatterns.paycenter.controller;

import com.jiu907.api.designpatterns.paycenter.model.PayDto;
import com.jiu907.api.designpatterns.paycenter.paystrategy.AbstractPayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author LeiLiMin
 * @Description: 模拟请求支付的Controller
 * @date: 2022/1/5
 */
@RestController
@RequestMapping("/payController")
public class PayController {
    @Autowired
    private Map<String, AbstractPayModel> producerPayModel;

    /**
     * 请求支付2
     */
    @PostMapping(path = "/pay")
    public Object requestPay(@RequestBody PayDto msg){
        AbstractPayModel aliPay = producerPayModel.get(msg.getPayModel());
        aliPay.payProcessor(msg.getExtra());
        return msg.getPayModel();
    }
}
