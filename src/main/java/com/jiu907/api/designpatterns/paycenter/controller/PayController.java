package com.jiu907.api.designpatterns.paycenter.controller;

import com.jiu907.api.designpatterns.paycenter.model.PayDto;
import org.springframework.web.bind.annotation.*;

/**
 * @Author LeiLiMin
 * @Description: 模拟请求支付的Controller
 * @date: 2022/1/5
 */
@RestController
@RequestMapping("/payController")
public class PayController {

    @PostMapping(path = "/pay")
    public Object requestPay(@RequestBody PayDto msg){
        System.out.println(msg);
        return msg.getPayModel();
    }
}
