package com.jiu907.api.designpatterns.paycenter.paystrategy;

/**
 * @Author LeiLiMin
 * @Description: 支付策略顶级父类
 *               主要是起到模版方法的作用，支付行为发生的时候都走这一套逻辑
 * @date: 2022/1/4
 */
public abstract class AbstractPayModel {

    /**
     * 支付行为的入口
     */
    public void payProcessor(){
        beforeProcessor();
        doProcessor();
        afterProcessor();
    }
    protected void beforeProcessor(){
    }

    /**
     * 子类实现
     */
    protected abstract void doProcessor();


    protected  void afterProcessor(){

    }


}
