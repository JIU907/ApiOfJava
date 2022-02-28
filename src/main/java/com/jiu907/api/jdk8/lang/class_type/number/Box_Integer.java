package com.jiu907.api.jdk8.lang.class_type.number;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/11
 */
public class Box_Integer {
    /**
     * 属性值
     * {@link Integer#MIN_VALUE}=0x80000000= 10000000 00000000 00000000 00000000
     * {@link Integer#MIN_VALUE}=0x7fffffff= 01111111 11111111 11111111 11111111
     * {@link Integer#value}    = int Value
     * {@link Integer#SIZE}     = Integer代表的是一个32位的int
     * {@link Integer#BYTES}    = Integer代表的是一个4字节的int
     */
    public static void main(String[] args) {
        // valueOf与parseInt的区别
        /**
         * 1.valueOf里层是调用了parseInt的
         * 2.valueOf对parseInt的结果进行"装箱"
         *   parseInt返回的是基础数据类型
         * 3.parseInt接受的参数是字符串类型
         *   valueOf的接受参数就各种各样了
         */
        Integer va = Integer.valueOf("1");
        int pa = Integer.parseInt("2");

        System.out.println(va);
        System.out.println(pa);

    }
}
