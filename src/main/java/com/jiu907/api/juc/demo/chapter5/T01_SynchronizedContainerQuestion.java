package com.jiu907.api.juc.demo.chapter5;

import java.util.Vector;

/**
 * @Author LeiLiMin
 * @Description: 同步容器的问题
 * @date: 2022/2/24
 */
public class T01_SynchronizedContainerQuestion {


    /**
     * 不安全操作--
     * Vector#size()
     * Vector#remove()
     * Vector#get()
     * 都被Synchronized修饰，但是方法操作并不是原子操作
     */
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void delLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }


    /**
     * 安全操作--
     * 1.Vector的方法用的是对象锁(非类锁)
     * 2.复合操作中对操作进行加对象锁 -> 确保锁一致
     */
    public static Object getLastSync(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void delLastSync(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
