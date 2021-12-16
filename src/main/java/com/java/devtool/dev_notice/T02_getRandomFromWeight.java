package com.java.devtool.dev_notice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: getRandomFromWeigh
 * @date: 2021/12/16
 */
public class T02_getRandomFromWeight {
    public static void main(String[] args) {
        List<Integer> weight=new ArrayList<>();
        weight.add(1);
        weight.add(2);
        weight.add(3);
        int result=-1;
        /**
         * why?
         * 1.根据上诉权重可得：1，2，2，3，3，3
         * 2.根据以下逻辑
         *      *：random出的值会很随机的遍布在上诉列表的某一点上
         *      *：而在每次循环不匹配时的扣除当次的值，这就将整个列表抽象成了一个域图
         */
        // 权重求和
        int weightAccMax = weight.stream().mapToInt(Integer::intValue).sum();
        // 求出random
        int randomWeight = ThreadLocalRandom.current().nextInt(0, weightAccMax);
        for (int i = 0; i < weight.size(); i++) {
            if (randomWeight < weight.get(i)) {
                result = weight.get(i);
                break;
            }
            randomWeight -= weight.get(i);
        }
        System.out.println(result);
    }
}
