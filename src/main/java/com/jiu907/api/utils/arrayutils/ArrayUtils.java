package com.jiu907.api.utils.arrayutils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/3/17
 */
public class ArrayUtils {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 0, 0, 0, 0, 0});
        int[] ints = list.get(0);
        for (int i1 = 0; i1 < ints.length; i1++) {
            ints[i1] = i1 * 3 + ints[i1];
        }

        for (int i : list.get(0)) {
            System.out.println(i);
        }
        // ============
        List<String> test = new ArrayList<>();
        test.add("1111");
        test.add(0, "-1-1-");
        for (String s : test) {
            System.out.println(s);
        }
        int[] betList = new int[]{1, 2, 3, 4, 5};
        String join = StringUtils.join(betList, ',');
        System.out.println(join);
    }
}
