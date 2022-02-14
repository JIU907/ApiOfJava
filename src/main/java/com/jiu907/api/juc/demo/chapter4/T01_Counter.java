package com.jiu907.api.juc.demo.chapter4;

/**
 * @Author LeiLiMin
 * @Description: 使用Java监视器模式的线程安全计数器
 * @date: 2022/2/14
 */
public final class T01_Counter {

    private long value = 0L;

    public synchronized long getValue() {
        return this.value;
    }

    public synchronized long incrValue() {
        if (value == Long.MAX_VALUE) {
            throw new RuntimeException("数据溢出");
        }
        return ++value;

    }
}
