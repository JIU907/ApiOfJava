package com.jiu907.api.newyear.demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/6
 */
public class T03_UnsafeCache {
    private final AtomicReference<Long> longNumber = new AtomicReference<>();
    private final AtomicReference<String> longString = new AtomicReference<>();

    /**
     * 线程不安全--
     * 这个方法对于longNumber和longString的存值操作并不能保证是原子的操作
     * 也就是不能保证longNumber和longString取值时保持对应关系
     * @param num
     */
    public void processor(Long num) {
        if (num.equals(longNumber.get())) {
            System.out.println(longString.get());
        } else {
            longNumber.set(num);
            longString.set(num + ":String");
        }
    }
}
