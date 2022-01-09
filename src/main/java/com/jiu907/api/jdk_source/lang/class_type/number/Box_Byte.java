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
        // testValueOfMethod(b);
        testCommonApi();
    }
    public static void testCommonApi(){
        /**
         * 其实与ValueOf的原理一致，就是将输入的agr转为10进制数
         * arg: 可以是16进制数也可以是10进制数
         */
        Byte decode = Byte.decode("0xA");
        System.out.println(decode);

        /**
         * 将Byte的值转为基础数值类型
         */
        byte byteValue = decode.byteValue();
        short shortValue = decode.shortValue();
        int intValue = decode.intValue();
        long longValue = decode.longValue();
        float floatValue = decode.floatValue();
        double doubleValue = decode.doubleValue();

        String s = decode.toString();

        /**
         * Byte 的hashCode可以是返回一个： int(value)
         */
        System.out.println(decode.hashCode());

        /**
         * Byte的equals()的判断流程是
         *      1.判断arg是否为Byte类型
         *      2.this.value==arg.byteValue()
         */
        System.out.println(decode.equals((byte) 10));

        /**
         * return: this.value-arg.byteValue()
         */
        System.out.println(decode.compareTo((byte)1));

        /**
         * 将this.value转为无符号Int
         * return: ((int) x) & 0xff;
         */
        System.out.println(Byte.toUnsignedInt((byte)-127));

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

        // parseByte
        /**
         * 为什么将parseBye放在ValueOf中一起讲解呢？
         *  因为：想要通过将String转为Byte的ValueOf()都是调用来该方法
         *         =>目的是将字符串转位一个进制表示的Byte
         *         =>返回的是一个基础数据类型的Byte
         */
        byte b1 = Byte.parseByte("1");
    }


}
