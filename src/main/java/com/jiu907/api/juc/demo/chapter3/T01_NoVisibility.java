package com.jiu907.api.juc.demo.chapter3;

/**
 * @Author LeiLiMin
 * @Description: 线程间变量不可见的案例
 * @date: 2022/2/7
 */
public class T01_NoVisibility {
    private static boolean flag = false;
    private static int num = 0;

    public static void main(String[] args) {
        /**
         * 情况1：出现了死循环 -> 正是由于多线程下的变量不及时可见导致的
         * 情况2：输出时，num远远大于5
         */
        new Thread(() -> {
            while (num != 5) {
            }
            System.out.println("num:" + num);
        }, "Thread-read").start();
        new Thread(() -> {
            while (true) {
                num++;
            }
        }, "Thread-write").start();

    }


    /**
     * 以下 get/set方法再遇到并发时，可能导致get的值未必时最新set的值
     */
    private int value;

    private int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    /**
     * 非volatile修饰的共享变量
     *
     * JMM要求--
     * 变量的读取操作和写入操作都必须时原子操作
     * 但是对于long和double这种64位变量，JVM允许将读写操作分解为2个32位操作
     *  这就导致了程序很有可能操作的是一个随机数，而不是我们曾经设置的一个数
     *
     */


    /**
     * 一种弱同步的方式--
     * 用来保证变量的可见性
     */
    private volatile int valueVisibility;

    private int getValueVisibility() {
        return valueVisibility;
    }

    private void setValueVisibility(int value) {
        this.valueVisibility = valueVisibility;
    }

}
