package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/15
 */
public class T05_BOX_Character {
    public static void main(String[] args) {
        System.out.println((int)Character.reverseBytes((char)3));
        /**
         * (((ch & 0xFF00) >> 8) | (ch << 8))
         * 字节反转：
         *      eg: A B
         *          B A
         */
        System.out.println(0xFF00);
    }
}
