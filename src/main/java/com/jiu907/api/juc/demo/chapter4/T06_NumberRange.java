package com.jiu907.api.juc.demo.chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author LeiLiMin
 * @Description: 2个状态变量之间存在不变性条件下的线程不安全演示
 * @date: 2022/2/22
 */
public class T06_NumberRange {
    /**
     * 线程不安全解释--
     * 1.lower和upper是存在关联条件的
     * 2.2个set方法采用了先检查后执行的方式
     * 3.这2个独立的set方法并不是原子操作
     * <p>
     * eg: lower=3
     * upper=6
     * <p>
     * Thread A 执行 setLower(5)   ->  5<6 成功
     * Thread B 执行 setUpper(4)   ->  4>3 成功
     * Thread A 执行 lower=5
     * Thread B 执行 upper=4
     * <p>
     * 结果错误
     */
    // 不变性条件 lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > this.upper.get()) {
            System.out.println("lower:" + this.lower + "\tupper:" + this.upper);
            throw new RuntimeException("lower > upper");
        }
        this.lower.set(i);
    }

    public void setUpper(int i) {
        if (i < this.lower.get()) {
            System.out.println("lower:" + this.lower + "\tupper:" + this.upper);
            throw new RuntimeException("upper < lower");
        }
        this.upper.set(i);
    }
}
