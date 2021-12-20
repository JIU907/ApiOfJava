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
     */
    public static void main(String[] args) {

        /**
         * String 被final修饰所以不可被继承
         * {@link String.value}被final修饰，所以String一旦初始化就不可被改变
         */

        // testArgOfNullInvokeConstructorMethod();

        // testConstructorMethodOfString();

        // String str = "编";
        // StringBuffer sb = new StringBuffer();
        // char [] source_char = str.toCharArray();
        // String unicode = null;
        // for (int i=0;i<source_char.length;i++) {
        //     unicode = Integer.toHexString(source_char[i]);
        //     if (unicode.length() <= 2) {
        //         unicode = "00" + unicode;
        //     }
        //     sb.append("\\u" + unicode);
        // }
        // System.out.println(sb);
        //
        // String testCode = "ab\uD83D\uDE03cd";
        // int length = testCode.length();
        // int count = testCode.codePointCount(0, testCode.length());
        // System.out.println((char)128515);
//length=6
//count=5
        testCommonApi("Hello");

    }

    public static void testCommonApi(String str) {
        // private final byte[] value;
        str.length(); // 输出byte[]的长度
        str.isEmpty();// return value.length == 0;
        str.charAt(1);// return value[index];   Maybe Appear Exception "StringIndexOutOfBoundsException"


        // 关于代码点的文章: https://blog.csdn.net/qlql489/article/details/82780716
        /**
         * 先理解什么是代码点：
         *     编码字符集中每一个字符都和一个编号对应。那么这个编号就是代码点（Code Point）
         *     那一个char就能表示一个字符吗？ 比如：😃
         *                                并不行，这个表情需要2个char
         */
        System.out.println("😃的长度:" + "😃".length());
        System.out.println("😃的长度:".codePointAt(0));    // 方法返回字符串中指定索引处的字符的Unicode值。
    }

    /**
     * 用null去调用String的构造函数会被抛出异常NullPointerException
     */
    public static void testArgOfNullInvokeConstructorMethod() {
        String s1 = null;
        String s = new String(s1);
    }

    /**
     * 总结一下String构造方法相关的小点
     * 1.String可以通过String,char[],int[],byte[]进行创建
     * 2.String通过Array进行初始化时，可以传入offset,count进行截取初始化
     * 3.String通过byte[]进行初始化时，可以传入字符集进行初始化
     * 4.String通过String Buffer和String Builder初始化
     */
    public static void testConstructorMethodOfString() {
        // 无参构造方法创建的对象是"";
        String blank = new String();
        // 通过传入String创建一个新的String对象，其实就是将字符数组和hash进行赋值
        String copyFromString = new String("abc");
        // 通过字符数组创建String
        char[] charArr = {'a', 'b'};
        String copyFromCharArr = new String(charArr);
        /**
         * 有三种情况会报错
         * 1.offset<0;
         * 2.count<0;
         * 3.offset>value.length-count
         * 一种情况返回blank
         * 1.count=0;offset<value.length
         */
        String copyFromCharArrByIndex = new String(charArr, 1, 0);
        System.out.println(copyFromCharArrByIndex);
        int[] intArr = {97, 98};
        String copyFromIntArrByIndex = new String(intArr, 1, 1);
        System.out.println(copyFromIntArrByIndex);

        byte[] byteArr = {0, 1, 2, 3, 4};
        String s = new String(byteArr, Charset.defaultCharset());
        System.out.println(s);
    }
}
