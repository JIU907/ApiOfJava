package com.jiu907.api.springboot.controller;

import com.jiu907.api.springboot.model.PayDto;
import com.jiu907.api.springboot.paycenter.paystrategy.AbstractPayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author LeiLiMin
 * @Description: 权限Controller
 * @date: 2022/1/5
 */
@RestController
@RequestMapping("/authController")
public class AuthController {
    @Autowired
    private Map<String, AbstractPayModel> producerPayModel;

    /**
     * 请求支付2
     */
    @PostMapping(path = "/pay")
    public Object requestPay(HttpServletRequest hp,@RequestBody PayDto msg){
        AbstractPayModel aliPay = producerPayModel.get(msg.getPayModel());
        aliPay.payProcessor(msg.getExtra());
        return msg.getPayModel();

    }
}
