package com.jiu907.api.verify;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: 双向算线
 * @date: 2022/3/9
 */
public class BothWayCalculate {
    // 赢钱线
    public static final List<List<Integer>> payLinesList;

    // 赢钱图标
    public static final Set<Integer> symbolNumberSet;

    static {
        // produce
        Integer[] line0 = new Integer[]{0, 0, 0, 0, 0};
        Integer[] line1 = new Integer[]{1, 1, 1, 1, 1};
        Integer[] line2 = new Integer[]{2, 2, 2, 2, 2};
        List<List<Integer>> result = new ArrayList();
        result.add(Arrays.asList(line0));
        result.add(Arrays.asList(line1));
        result.add(Arrays.asList(line2));
        payLinesList = result;

        HashSet<Integer> tempSet = new HashSet<>();
        tempSet.add(1);
        tempSet.add(2);
        tempSet.add(3);
        tempSet.add(4);
        tempSet.add(5);
        symbolNumberSet = tempSet;


    }

    /**
     * 1.迭代赢钱线
     * 2.计算双线
     * *: 单线求最大值
     */
    public static void main(String[] args) {
        // 1.生产棋盘 [3*5],先排后列
        List<List<Integer>> spinResult = genSpinResult();

        // 2.算线
        for (List<Integer> payLine : payLinesList) {

            // payLine  下标:列 , 值:行
            for (int i = 0; i < payLine.size(); i++) {
                List<Integer> integers = spinResult.get(payLine.get(i));
                Integer value = integers.get(i);

                // 第一个图标都不是"赢钱图标"
                if (!symbolNumberSet.contains(value) && i == 0) {
                    return;
                }
            }


        }
    }

    /**
     * 模拟生产赢钱线 3*5 机台
     *
     * @return
     */
    public static List<List<Integer>> genSpinResult() {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> linesResult = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                int value = ThreadLocalRandom.current().nextInt(10);
                linesResult.add(value);
            }
            result.add(linesResult);
        }
        return result;
    }
}
