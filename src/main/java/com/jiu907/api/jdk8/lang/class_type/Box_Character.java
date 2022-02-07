package com.jiu907.api.jdk8.lang.class_type;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/15
 */
public class Box_Character {
    public static void main(String[] args) {
        /**
         * (((ch & 0xFF00) >> 8) | (ch << 8))
         * 字节反转：
         *      input:   0000 0000 0000 0011
         *      output： 0000 0011 0000 0000
         */
        System.out.println("reverseBytes:"+(int) java.lang.Character.reverseBytes((char)3));

        /**
         * Character attribute that 2 bytes(16 bits) of size
         */
        System.out.println("Character.BYTES:"+ java.lang.Character.BYTES);
        System.out.println("Character.SIZE:"+ java.lang.Character.SIZE);
        /**
         * 主要还是使用到了穷举的方式进行转换
         */
        System.out.println("toUpperCase:"+ java.lang.Character.toUpperCase('a'));

        /**
         * 是否是java命名规范中许可的首字母
         */
        System.out.println(java.lang.Character.isJavaLetter('_'));
        /**
         * check both of letter and digit
         */
        System.out.println(java.lang.Character.isLetterOrDigit('1'));

    }
}
