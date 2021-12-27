package com.java.devtool.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/27
 */
public class test {
    public static void main(String[] args) {
        pre();
        testIf();
        testNum();
    }

    public static void pre(){
        long begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000000; j++) {

            }
        }
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("pre ["+(end-begin)+"]");
    }
    public static void testIf(){
        int a=1;
        long begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (int i = 0; i < 100000000; i++) {
            if(a>1){
            }else{

            }
        }
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("if else time["+(end-begin)+"]");
    }
    public static void testNum(){
        int a=1;
        long begin = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (int i = 0; i < 100000000; i++) {
          a=a++;
        }
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("num ["+(end-begin)+"]");
    }
}
