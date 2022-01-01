package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description: String Builder学习
 * @date: 2021/12/30
 */
public class T07_StringBuilder {
    /**
     * StringBuilder是一个可变的字符序列
     * 提供一些API兼容StringBuffer，但是是不保证同步的
     * 设计该类的原因：在一些地方替换StringBuffer使用单一线程的问题
     * 使用场景：并发问题不存在，或者效率优先的场景
     * 主要操作：字符串中append,insert字符
     */
    public static void main(String[] args) {
    //    testConstructorMethodOfStringBuilder();
        testAppendApi("StringBuilder:");
        StringBuilder test=new StringBuilder("");
    }

    public static void testAppendApi(String str){
        // StringBuilder只有到最后toString时才会创建String对象，而中途不断的append只是在常量池中存入这些字面量
        StringBuilder sb=new StringBuilder(str);
        /**
         * 总的来说：1.append方法就是像StringBuilder的尾部追加字符串，如果传入为null，那就在尾部插入"null"
         *         2.单个参数的方法可以接收所有CharSequence的子类
         */
        System.out.println(sb.append(str));

        /**
         * 入参为字符数组
         */
        sb=new StringBuilder(str);
        char[] charArr=new char[]{'a','b','c'};
        System.out.println(sb.append(charArr));

        /**
         * arg: CharSequence
         *      [begin,end)
         * return: this
         * 向StringBuilder的结尾处追加入参的子串
         */
        String testAppendSub="SubString";
        sb=new StringBuilder(str);
        System.out.println(sb.append(testAppendSub, 0, 3));


        /**
         * arg: char[]
         *      int beginIndex
         *      int length
         * return: this+[char[index],char[index+length])
         * 向StringBuilder的结尾处追加入参的子数组
         */
        charArr=new char[]{'a','b','c'};
        sb=new StringBuilder(str);
        System.out.println(sb.append(charArr, 0, 2));

        /**
         * append:Boolean
         * 这里是举例一下：其实还可以传入：int，long，double，char and so on
         */
        sb=new StringBuilder(str);
        System.out.println(sb.append(false));

        /**
         * 方法追加码点codePoint 参数到此序列的字符串表示形式。该参数被附加到这个序列中的内容。
         */
        sb=new StringBuilder(str);
        System.out.println(sb.appendCodePoint(67));

    }
    public static void testConstructorMethodOfStringBuilder(){
        // 1.默认开辟16字节的数组，2.判断是否禁用了字符串压缩
        StringBuilder stringBuilder = new StringBuilder();

        // 1.使用args，为字节数组开辟空间
        StringBuilder stringBuilderByUseCapacity=new StringBuilder(1);

        // 1.先开辟arg+16字节的数组空间 2.然后调用append(String)，向StringBuilder中追加数组
        StringBuilder stringBuilderByArg=new StringBuilder("args");

        // 1.那StringBuffer距离，arg可以是其他字符序列类型 2.先开辟arg+16字节的数组空间 3.然后调用append(String)，向StringBuilder中追加数组
        StringBuilder stringBuilderByOtherCharSequence=new StringBuilder(new StringBuffer("1"));
    }
}