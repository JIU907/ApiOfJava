package com.java.devtool.stream;

import com.java.devtool.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LeiLiMin
 * @Date 2021/11/12 17:38
 * @Description 测试 分组
 */
public class T03_groupMethod {
    public static void main(String[] args) {
        List<User> users = User.getUsers();
        Map<String, Integer> nameAgeOfMap = users.stream().collect(Collectors.toMap(User::getName, User::getAge));

        // result
        /**
         * {Mike=3, Lewis=1, Lucy=2, Lily=4}
         */
        System.out.println(nameAgeOfMap);
    }
}
