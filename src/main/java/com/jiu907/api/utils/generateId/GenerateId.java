package com.jiu907.api.utils.generateId;

import org.apache.maven.project.ProjectSorter;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: ID生成
 * 不触发，且随机
 * @date: 2022/1/6
 */
public class GenerateId {
    // 前缀号池
    public static List<Integer> prefixList = new ArrayList<>();
    // ID号池
    public static List<Integer> idList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(useLua());
    }

    public static void useJava() {
        // 1.生产有序集合
        for (int i = 0; i < 1000; i++) {
            prefixList.add(i);
            idList.add(i);
        }

        // 2.准备结果集
        HashSet<Integer> resultSet = new HashSet<>();

        /**
         * 生产ID主逻辑
         *  1.从前缀号池中选出一个前缀，并将其从号池中移除
         *  2.从ID号池中选取ID，并将其从ID号池中移除
         *  3.直到ID号池中的ID被用完，再回到步骤1
         */
        while (prefixList.size() > 0) {
            int prefix = ThreadLocalRandom.current().nextInt(prefixList.size());
            Integer prefixRemove = prefixList.remove(prefix);
            while (idList.size() > 0) {
                int id = ThreadLocalRandom.current().nextInt(idList.size());
                Integer idRemove = idList.remove(id);
                int i = prefixRemove * 1000 + idRemove;
                resultSet.add(i);
            }
            for (int i = 0; i < 1000; i++) {
                idList.add(i);
            }
        }
        System.out.println("resultSet" + resultSet.size());

    }

    public static int useLua() {
        File file = new File("/Users/leilimin/IDEA-MySpace/ApiOfJava/src/main/java/com/jiu907/api/utils/generateId/GenerateId.lua");
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileContentStr = new String(fileContent);
        Jedis jedis = new Jedis("1.15.151.138", 6379);
        jedis.auth("123456");
        Object eval = jedis.eval(fileContentStr);
        return Objects.isNull(eval) ? 0 : Integer.parseInt(eval.toString());
    }
}