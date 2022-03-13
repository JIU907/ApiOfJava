package com.jiu907.api.jdk8;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/3/13
 */
public class T04_ThreadLocalRandom {
    // 线程安全的随机数生产
    public static void main(String[] args) {
        float v = ThreadLocalRandom.current().nextFloat();
        System.out.println(v);
        System.out.println((int) (v * 100));
    }
}
