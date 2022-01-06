package com.jiu907.api.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: ID生成
 *               不触发，且随机
 * @date: 2022/1/6
 */
public class GenerateId {
    public static int prefix=0;
    public static int id=0;
    public static final int base=10000;
    public static void main(String[] args) {
        /**
         *
         * 2.随机取出值
         * 3.取出的值存入记忆集合
         */
        List<Integer> prefixList=new ArrayList<>();
        List<Integer> idList=new ArrayList<>();

        // 1.有序集合
        for (int i = 0; i < 1000; i++) {
            prefixList.add(i);
            idList.add(i);
        }
        HashSet<Integer> prefixSet=new HashSet<>();
        HashSet<Integer> idSet=new HashSet<>();
        HashSet<Integer> resultSet=new HashSet<>();

        while(prefixList.size()>0){
            int prefix = ThreadLocalRandom.current().nextInt(prefixList.size());
            Integer prefixRemove = prefixList.remove(prefix);
            while(idList.size()>0){
                int id = ThreadLocalRandom.current().nextInt(idList.size());
                Integer idRemove = idList.remove(id);
                prefixSet.add(prefixRemove);
                idSet.add(idRemove);
                int i=prefixRemove *1000+idRemove;
                resultSet.add(i);
            }
            for (int i = 0; i < 1000; i++) {
                idList.add(i);
            }
        }
        System.out.println("resultSet"+resultSet.size());
        System.out.println("prefixSet"+prefixSet.size());
        System.out.println("idSet"+idSet.size());

        // 前缀基数：10000
        // 前缀生成：xxxxx
        System.out.println(100000|99);
    }
}
