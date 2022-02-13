package com.jiu907.api.jdk8;

import java.util.Optional;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/8
 */
public class T02_Optional {
    public static void main(String[] args) {
        // of();
        get();
    }

    /**
     * 内存存在非空校验
     */
    private static void of() {
        Long numA = null;
        Long numB = 1L;
        try {
            Optional.of(numB);
            System.out.println("num B over");

            Optional.of(numA);
            System.out.println("num A over");
        } catch (Exception e) {
            System.out.println("x error");
        }
    }

    /**
     * 取值操作--
     * 如果optional.value=null，就会抛出异常
     */
    private static void get() {
        Long numA = 1L;
        Long getNum = Optional.of(numA).get();
        System.out.println(getNum);

    }

    /**
     * 如果optional.value=null
     * 返回orElse中的值
     */
    private static void orElse() {
        Long num = null;
        Optional.ofNullable(num).orElse(1L);
    }
}
