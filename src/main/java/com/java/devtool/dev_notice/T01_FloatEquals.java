package com.java.devtool.dev_notice;

import java.util.Objects;

/**
 * @Author LeiLiMin
 * @Description: 浮点数如何判断是否相等
 * @date: 2021/12/13
 */
public class T01_FloatEquals {
    public static void main(String[] args) {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        float diff = 1e-6F;
        if (Math.abs(a - b) < diff) {
            System.out.println("true");
        }
        // box
        System.out.println("====box====");
        Float a1=1.0F;
        Float b1=1.00F;
        System.out.println(a1==b1);         // false;
        System.out.println(a1.equals(b1));  // true;

        // basic
        System.out.println("====basic====");
        float a2=1.0F;
        float b2=1.00F;
        System.out.println(Objects.equals(a2,b2));  // true;
        System.out.println(a2==b2);                 // true;
        System.out.println("==========");
        double fa=0.33F;
        long fl=10L;
        System.out.println(fa*fl);
    }
}
