package com.java.devtool.stream;

import com.java.devtool.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LeiLiMin
 * @Date 2021/11/11 19:13
 * @Description 取出集合中想要的元素，成为新的集合
 */
public class T01_mapMethod {
    public static void main(String[] args) {
        List<User> users = User.getUsers();
        List<Integer> ageList = users.stream().map(e -> e.getAge()).collect(Collectors.toList());
        System.out.println(ageList);
    }
}
