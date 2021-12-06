package com.java.devtool.stream.model;

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

    public static List<User> getUsers(){
        List<User> list=new ArrayList<>();
        list.add(new User(1,true,"Lewis"));
        list.add(new User(2,false,"Lucy"));
        list.add(new User(3,true,"Mike"));
        list.add(new User(4,false,"Lily"));
        return list;
    }
}
