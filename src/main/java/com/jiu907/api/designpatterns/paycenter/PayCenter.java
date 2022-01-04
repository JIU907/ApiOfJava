package com.jiu907.api.designpatterns.paycenter;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/4
 */
public abstract class PayCenter {
    public void payProcessor(){
        beforeProcessor();
        doProcessor();
        afterProcessor();
    }
    public void beforeProcessor(){

    }

    // 1.验单
    // 2.下单
    // 3.结单
    public abstract void doProcessor();


    public  void afterProcessor(){

    }


}
