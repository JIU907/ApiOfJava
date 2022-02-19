package com.jiu907.api.juc.demo.chapter4;

/**
 * @Author LeiLiMin
 * @Description: 私有锁
 * @date: 2022/2/19
 */
public class T03_PrivateLock {
    private final Object myLock = new Object();

    /**
     * 该程序展示如何通过私有锁来保护对象的状态
     *
     * myLock是一个私有锁
     */
    void someMethod() {
        synchronized (myLock) {
            // do something...
        }
    }
}
