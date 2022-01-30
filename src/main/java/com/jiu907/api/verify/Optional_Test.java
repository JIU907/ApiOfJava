package com.jiu907.api.verify;

import java.util.Optional;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/14
 */
public class Optional_Test {
    public static void main(String[] args) {
        String str = null;
        // 允许arg为null
        Optional<String> str2 = Optional.ofNullable(str);

        // 返回： value!=null
        System.out.println(str2.isPresent());

        // return: value
        // String s = str2.get();

        // 如果arg=null,会抛出异常
        // Optional<String> str1 = Optional.of(str);

        //  value != null ? value : other;
        // String s1 = str1.orElse("1");

        Long l1=null;
        Long aLong = Optional.ofNullable(l1).orElse(0L);
        System.out.println(aLong);
    }
}
