package com.jiu907.api.jdk8.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeiLiMin
 * @Date 2021/11/11 19:14
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer age;
    private Boolean sex;
    private String name;
    private Address add;

    public static List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, true, "Lewis", new Address()));
        list.add(new User(2, false, "Lucy", new Address()));
        list.add(new User(3, true, "Mike", new Address()));
        list.add(new User(4, false, "Lily", new Address()));
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String province;
        private String city;
        private String county;
    }
}
