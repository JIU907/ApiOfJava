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
}
