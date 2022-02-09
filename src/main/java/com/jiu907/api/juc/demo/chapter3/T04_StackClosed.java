package com.jiu907.api.juc.demo.chapter3;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.*;

/**
 * @Author LeiLiMin
 * @Description: 线程封闭
 * @date: 2022/2/9
 */
public class T04_StackClosed {
    /**
     * 栈封闭：
     * 1.count是局部变量，以线程的角度，封闭在自己的局部变量表中，无法被其他线程获取到
     * 2.userSet也封闭在了局部变量表中，但是其内部的User对象仍然会被其他线程修改
     * @param users
     */
    public void loadTheArk(List<User> users) {
        int count = 0;
        Set<User> userSet=new HashSet<>();
        for (int i = 0; i < users.size(); i++) {
            count++;
            userSet.add(users.get(i));
        }
    }
}
