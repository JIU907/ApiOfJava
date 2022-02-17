package com.jiu907.api.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        // 3.获取当前时区-当日最小时间 00:00:00 的毫秒值
        long min = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        // 4.获取当前时区-当日最小时间 23:59:59 的毫秒值
        long max = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(milliSecond.equals(System.currentTimeMillis())); //true
    }
}
