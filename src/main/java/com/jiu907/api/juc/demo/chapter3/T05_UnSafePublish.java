package com.jiu907.api.juc.demo.chapter3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: 不安全发布
 * @date: 2022/2/10
 */
public class T05_UnSafePublish {
    /**
     * 为什么不安全？
     * A: 线程A调用T05_UnSafePublish#initialize()方法 -> 线程A可以看到holder的最新值
     *    线程B调用T05_UnSafePublish.holder字段       -> 因为在自己的局部变量表中，所以可能看到的是旧值
     *    这就导致了2个线程看到的holder是不一致的
     *
     * B: 调用Holder#assertSanity()
     *    线程B第一次读取到了holder还未初始化之前的默认值0
     *    此时线程A更新了holder.n
     *    线程B第二次读取到了holder.n的新值
     */
    public Holder holder = new Holder(-1);

    public void initialize(int n) {
        this.holder = new Holder(n);
    }

    public static void main(String[] args) {
        T05_UnSafePublish unsafePublish = new T05_UnSafePublish();
        while (true) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    unsafePublish.initialize(ThreadLocalRandom.current().nextInt());
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    unsafePublish.holder.assertSanity();
                }
            });
            t2.start();

            t1.start();
        }
    }
}


class Holder {
    private int n;


    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (this.n != this.n) {
            throw new RuntimeException("unsafe publish");
        }
    }
}
