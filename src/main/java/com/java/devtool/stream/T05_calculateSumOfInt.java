package com.java.devtool.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/16
 */
public class T05_calculateSumOfInt {
    public static void main(String[] args) {
        List<Integer> weight = new ArrayList<>();
        weight.add(1);
        weight.add(3);
        weight.add(5);
        weight.add(7);

        int weightAccMax = weight.stream().mapToInt(Integer::intValue).sum();
    }

}
