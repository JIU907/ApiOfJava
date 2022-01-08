package com.jiu907.api.jdk_source.lang.class_type.number;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/3
 */
public class Box_Byte {
    /**
     * {@link Byte}:是基础数据类型byte的包装类型
     * 它定义了2个常量
     *      {@link Byte#value}: Byte变量的值
     *      {@link Byte#MIN_VALUE}: -128
     *      {@link Byte#MAX_VALUE}:  127
     *      {@link Byte.ByteCache}: 这个私有的内部类其实缓存了[-128,127]的Byte
     */
    public static void main(String[] args) {
        byte b =1;
        testValueOfMethod(b);
    }

    public static void testValueOfMethod(byte b){
        /**
         * 可以理解为将一个基础数据类型的byte转为包装类型的Byte
         */
        System.out.println(Byte.valueOf(b));

        /**
         * 字符串表示的byte转为包装类型的Byte
         */
        System.out.println(Byte.valueOf("-1"));

        /**
         * 将16进制表示的"A"转为10进制，以Byte的形式来存储
         */
        System.out.println(Byte.valueOf("A", 16));
    }
}
