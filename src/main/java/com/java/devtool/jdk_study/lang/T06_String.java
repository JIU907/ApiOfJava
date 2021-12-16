package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/16
 */
public class T06_String {
    /**
     * 1.在Java程序中String代表一连串的字符
     * 2.String是常量，在它们创建之后是不可改变的，String Buffer是支持改变的
     * 3.String的拼接是通过String builder和String Buffer实现
     * 4.用null去调用String的构造函数会被抛出异常NullPointerException
     * @param args
     */
    public static void main(String[] args) {
        testArgOfNullInvokeConstructorMethod();
    }
    public static void testArgOfNullInvokeConstructorMethod(){
        String s1=null;
        String s = new String(s1);
    }
}
