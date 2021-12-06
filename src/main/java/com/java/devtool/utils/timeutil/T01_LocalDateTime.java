package com.java.devtool.utils.timeutil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author LeiLiMin
 * @Date 2021/11/10 15:31
 * @Description Jdk 8: 时间类
 */
public class T01_LocalDateTime {
    public static void main(String[] args) {
        // 1.获取 时区当前时间毫秒值
        Long nowMills = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
