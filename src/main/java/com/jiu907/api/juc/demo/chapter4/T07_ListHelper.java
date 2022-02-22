package com.jiu907.api.juc.demo.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/22
 */
public class T07_ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    /**
     * 线程安全吗？
     * 不安全
     * 1.putIfAbsent的锁是T07_ListHelper.Class的类锁
     * 2.list的锁是list的对象锁
     * 3.我们可以阻止通过T07_ListHelper进行操作list的并发问题
     * 但是不能阻止同时由T07_ListHelper发起的操作和另外一个线程直接操作list的操作
     *
     * @return
     */
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }

    /**
     * 线程安全
     * <p>
     * 2把锁都是一致的
     *
     * @return
     */
    public boolean putIfAbsentSafe(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
