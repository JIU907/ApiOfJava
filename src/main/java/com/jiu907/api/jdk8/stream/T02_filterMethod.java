package com.jiu907.api.jdk8.stream;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LeiLiMin
 * @Date 2021/11/12 17:34
 * @Description 测试 过滤
 */
public class T02_filterMethod {
    public static void main(String[] args) {
        List<User> users = User.getUsers();
        // 将filter中返回结构为ture的元素保留下来，过滤为false的元素
        List<User> males = users.stream().filter(e -> e.getSex() == true).collect(Collectors.toList());

        // result
        /**
         * User(age=1, sex=true, name=Lewis),
         * User(age=3, sex=true, name=Mike)
         */
        System.out.println(males);
    }
}
