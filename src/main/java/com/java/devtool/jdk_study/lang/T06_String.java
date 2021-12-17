package com.java.devtool.jdk_study.lang;

import java.nio.charset.Charset;

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
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * String 被final修饰所以不可被继承
         * {@link String.value}被final修饰，所以String一旦初始化就不可被改变
         */

        testArgOfNullInvokeConstructorMethod();







    }

    public static void testArgOfNullInvokeConstructorMethod() {
        String s1 = null;
        String s = new String(s1);
    }

    /**
     * 总结一下String构造方法相关的小点
     *  1.String可以通过String,char[],int[],byte[]进行创建
     *  2.String通过Array进行初始化时，可以传入offset,count进行截取初始化
     *  3.String通过byte[]进行初始化时，可以传入字符集进行初始化
     *  4.String通过String Buffer和String Builder初始化
     */
    public static void testConstructorMethodOfString(){
        // 无参构造方法创建的对象是"";
        String blank = new String();
        // 通过传入String创建一个新的String对象，其实就是将字符数组和hash进行赋值
        String copyFromString = new String("abc");
        // 通过字符数组创建String
        char[] charArr= {'a','b'};
        String copyFromCharArr = new String(charArr);
        /**
         * 有三种情况会报错
         * 1.offset<0;
         * 2.count<0;
         * 3.offset>value.length-count
         * 一种情况返回blank
         * 1.count=0;offset<value.length
         */
        String copyFromCharArrByIndex=new String(charArr,1,0);
        System.out.println(copyFromCharArrByIndex);
        int[] intArr= {97,98};
        String copyFromIntArrByIndex=new String(intArr,1,1);
        System.out.println(copyFromIntArrByIndex);

        byte[] byteArr={0,1,2,3,4};
        String s = new String(byteArr, Charset.defaultCharset());
        System.out.println(s);
    }
}
