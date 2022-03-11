package com.jiu907.api.juc.demo.chapter5;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/3/11
 */
public class T02_ConcurrentContainer {
    public static void main(String[] args) {
        // 11.3.4会对该容器进行剖析
        ConcurrentHashMap<String, Object> concurrentMap = new ConcurrentHashMap<>();

        // 写时复制List  元素副本由Volatile修饰，可以让副本的变化及时被其他线程感知
        CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();
        /**
         * 1.先获取副本
         * 2.copy副本
         * 3.添加元素
         * 4.设置新副本
         */
        concurrentList.add("");

        /**
         * 1.获取当前副本
         * 2.直接取值
         */
        concurrentList.get(0);
    }
}
