package com.jiu907.api.juc.demo.chapter2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author LeiLiMin
 * @Description: 无状态对象
 * @date: 2022/2/6
 */
public class T01_NoStateClass {
    /**
     * 无状态对象--
     * 不包含任何域
     * 不包含其他类中域的引用
     * 计算过程中的临时状态都保存在线程栈中的局部变量表
     *
     * @param bound
     * @return
     */
    public static Integer getNum(Integer bound) {
        int random = ThreadLocalRandom.current().nextInt(bound);
        return random;
    }

    private int a = 0;

    /**
     * 并非线程安全--
     * a++: 并发原子操作
     * 该对象可能被并发访问
     *
     * @return
     */
    public int increaseA() {
        this.a++;
        return this.a;
    }

    private AtomicInteger atomicA = new AtomicInteger(0);

    /**
     * 打破了竞态条件--
     * 累加操作是原子的
     * @return
     */
    public int increaseAtomicA() {
        return this.atomicA.incrementAndGet();
    }
}
