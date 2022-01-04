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
    public abstract void beforeProcessor();
    public abstract void doProcessor();
    public abstract void afterProcessor();


}
