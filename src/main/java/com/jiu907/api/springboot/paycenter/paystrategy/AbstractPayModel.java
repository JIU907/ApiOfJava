package com.jiu907.api.springboot.paycenter.paystrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LeiLiMin
 * @Description: 支付策略顶级父类
 *               主要是起到模版方法的作用，支付行为发生的时候都走这一套逻辑
 * @date: 2022/1/4
 */
public abstract class AbstractPayModel {
    /**
     * 存储实际上下文
     */
    public Map<String,Object> contextMap=new HashMap<>();
    /**
     * 支付行为的入口
     */
    public void payProcessor(String payload){
        beforeProcessor();
        doProcessor(payload);
        afterProcessor();
    }

    // 业务逻辑前置检查--可以为abstract
    protected void beforeProcessor(){
    }

    // 主逻辑
    protected abstract void doProcessor(String payload);

    // 业务逻辑后置检查--可以为abstract
    protected  void afterProcessor(){

    }


}
