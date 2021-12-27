package com.java.devtool.utils.timeutil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author LeiLiMin
 * @Date 2021/11/10 15:31
 * @Description Jdk 8: 时间类
 */
public class T01_LocalDateTime {
    public static void main(String[] args) {
        // 1.获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        // 2.获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();


        // [0,num);
        for (int i = 0; i < 99999; i++) {
            int i1 = ThreadLocalRandom.current().nextInt(4);
            if(i1==0){
                System.out.println("yes");
                break;
            }
        }
    }
}
