package com.java.devtool.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiLiMin
 * @Description: Slots机台生产问题求一个海岛面积
 * @date: 2021/12/31
 */
public class T01_IslandArea {
    /**
     * 题目：在一哥5*5的棋盘内，陆地为1，海洋为0
     * [0,0,0,0,0]
     * [1,0,0,0,0]
     * [1,0,0,0,0]
     * [0,1,0,1,1]
     * [0,1,0,1,1]
     * 陆地的上下左右如果还有陆地相连接，那么该陆地可以合并
     * 1.一列只会连续的出现0，1，2个陆地
     * 求：求各个陆地的面积
     */
    public static void main(String[] args) {
        // 棋盘上的数据，按列转为数字
        int[] arr1 = {0, 0, 0, 1, 1};
        int line1 = 0;
        int temp1 = 0;
        int[] arr2 = {0, 0, 1, 1, 0};
        int line2 = 0;
        int temp2 = 0;
        int[] arr3 = {0, 0, 0, 1, 1};
        int line3 = 0;
        int temp3 = 0;
        int[] arr4 = {0, 1, 1, 0, 0};
        int line4 = 0;
        int temp4 = 0;
        int[] arr5 = {0, 0, 0, 1, 1};
        int line5 = 0;
        int temp5 = 0;
        for (int i = 0; i < 5; i++) {
            temp1 = arr1[5 - i - 1];
            temp2 = arr2[5 - i - 1];
            temp3 = arr3[5 - i - 1];
            temp4 = arr4[5 - i - 1];
            temp5 = arr5[5 - i - 1];

            for (int j = 0; j < i; j++) {
                temp1 <<= 1;
                temp2 <<= 1;
                temp3 <<= 1;
                temp4 <<= 1;
                temp5 <<= 1;
            }
            line1 |= temp1;
            line2 |= temp2;
            line3 |= temp3;
            line4 |= temp4;
            line5 |= temp5;


        }
        List<Integer> source = new ArrayList<>();
        source.add(line1);
        source.add(line2);
        source.add(line3);
        source.add(line4);
        source.add(line5);

        System.out.println(processor(source));

    }

    // 主要流程处理
    public static List<Integer> processor(List<Integer> source) {
        List<Integer> result = new ArrayList<>();
        int begin = 0;
        for (int i = 1; i < source.size(); i++) {
            int flag = source.get(i) & source.get(i - 1);
            if (flag > 0) {
                continue;
            };
            Integer bitCount=0;
            for(;begin<i;begin++){
                 bitCount += getBitCount(source.get(begin));
            }
            result.add(bitCount);
        }
        // 最后一轮校验
        int flag = source.get(source.size()-1) & source.get(source.size()-2);
        if (flag > 0) {
            Integer bitCount=0;
            for(;begin<source.size();begin++){
                bitCount += getBitCount(source.get(begin));
            }
            result.add(bitCount);

        }else{
            result.add(getBitCount(source.get(source.size()-1)));
        }
        return result;
    }

    /**
     * 获取bit中1的个数
     */
    public static Integer getBitCount(Integer arg) {
        int count = 0;
        while (arg != 0) {
            count++;
            // 最右侧的1
            int rightOne = arg & (~arg + 1);
            arg ^= rightOne; // 0^0=0 1^0=1,1^1=0;
        }
        return count;
    }
}
