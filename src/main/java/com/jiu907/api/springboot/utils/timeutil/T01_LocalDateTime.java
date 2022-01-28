package com.jiu907.api.springboot.utils.timeutil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
        System.out.println(milliSecond.equals(System.currentTimeMillis())); //true

    }
}
