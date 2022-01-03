package com.jiu907.api.stream;

import com.jiu907.api.stream.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LeiLiMin
 * @Date 2021/11/12 18:00
 * @Description 测试拼接
 */
public class T04_joiningMethod {
    public static void main(String[] args) {
        List<User> users = User.getUsers();
        String nameJoin = users.stream().map(User::getName).collect(Collectors.joining(","));

        /**
         * Lewis,Lucy,Mike,Lily
         */
        System.out.println(nameJoin);
    }
}
