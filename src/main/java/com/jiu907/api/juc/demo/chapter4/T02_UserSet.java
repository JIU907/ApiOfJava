package com.jiu907.api.juc.demo.chapter4;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/19
 */
public class T02_UserSet {
    /**
     * 1.UserSet的状态由mySet决定
     * 2.mySet是UserSet的私有变量并且不会逸出(外部无法获取到)
     *   -> hashSet对象被封闭在UserSet内
     * 3.唯一能访问到mySet对象的代码路径都需要获取到synchronized锁，UserSet的状态完全由它的内置锁来保护
     * 4.所以UserSet是一个线程安全的类
     */
    private final Set<User> mySet = new HashSet<>();

    public synchronized void addUser(User u) {
        mySet.add(u);
    }

    public synchronized boolean containUser(User u) {
        return mySet.contains(u);
    }
}
