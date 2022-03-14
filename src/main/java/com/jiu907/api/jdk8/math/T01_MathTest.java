package com.jiu907.api.jdk8.math;

import java.util.Arrays;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/3/14
 */
public class T01_MathTest {
    public static void main(String[] args) {
        float a = 2.0F;
        double ceil = Math.floor(a);
        System.out.println((int) ceil);

        Long[] betList = new Long[]{10000L, 20000L, 50000L, 100000L, 150000L};
        long totalBets = 10000L;
        System.out.println(Arrays.asList(betList).contains(totalBets));

        System.out.println("38404872aa4cd7ce27dc47edfac0fd".length());
    }
}
