package com.java.devtool.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/16
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> weight=new ArrayList<>();
        weight.add(1);
        weight.add(1);
        weight.add(1);
        weight.add(1);

        // 权重求和
        int weightAccMax = weight.stream().mapToInt(Integer::intValue).sum();
        // 求出random
        int randomWeight = ThreadLocalRandom.current().nextInt(0, weightAccMax);

        System.out.println(randomWeight);
    }
}
