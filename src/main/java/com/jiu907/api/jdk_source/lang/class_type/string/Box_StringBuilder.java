package com.jiu907.api.jdk_source.lang.class_type.string;

/**
 * @Author LeiLiMin
 * @Description: String Builder学习
 * @date: 2021/12/30
 */
public class Box_StringBuilder {
    /**
     * StringBuilder是一个可变的字符序列
     * 提供一些API兼容StringBuffer，但是是不保证同步的
     * 设计该类的原因：在一些地方替换StringBuffer使用单一线程的问题
     * 使用场景：并发问题不存在，或者效率优先的场景
     * 主要操作：字符串中append,insert字符
     */
    public static void main(String[] args) {
    //    testConstructorMethodOfStringBuilder();
    //     testAppendApi("StringBuilder:");
        testCommonApi("StringBuilder:");
    //     testInsertApi("StringBuilder:");
    }


    public static void testInsertApi(String str){
        StringBuilder sb=new StringBuilder(str);
        char[] insertArr=new char[]{'a','b','c'};
        /**
         * arg: index=>原StringBuilder的字符数组的下标位置
         *             代表着在这位置之前插入元素
         *      char[]=>要插入的数组
         *      offset=>插入数组的开始下标
         *              eg：如果输入0，就代表从'a'就开始取元素了
         *                  如果输入1，就代表从'b'就开始取元素
         *      len   =>要插入的数组长度
         */
        System.out.println(sb.insert(sb.length(), insertArr, 1, 2));

        /**
         * arg : offset: 原StringBuilder的字符数组的下标位置
         *               代表着在这位置之前插入元素
         *       Object: 在offset位置上插入元素
         * ----------------------------------------------
         * 类似的第二参数还可以是：char[],boolean,CharSequence,int,long,double,float
         */
        sb=new StringBuilder(str);
        String insertStr="insertTest";
        System.out.println(sb.insert(0, insertStr));

        sb=new StringBuilder(str);
        StringBuilder insertBuilder=new StringBuilder("insertTest");
        /**
         * arg : offset: 原StringBuilder的字符数组的下标位置
         *               代表着在这位置之前插入元素
         *       CharSequence: 要插入的元素
         *       start: 要插入元素的开始位置
         *       end: 要插入元素的结束位置
         *       !!! [start,end)
         */
        System.out.println(sb.insert(sb.length(), insertBuilder, 1, 2));


    }

    public static void testCommonApi(String str){
        //    测试一些其他方法，such as：delete
        StringBuilder sb=new StringBuilder(str);
        /**
         * 删除StringBuilder上[beginIndex,endIndex)上的字符
         */
        System.out.println(sb.delete(0, 1));

        sb=new StringBuilder(str);
        /**
         * 删除StringBuilder指定位置上的字符
         */
        System.out.println(sb.deleteCharAt(0));

        sb=new StringBuilder(str);
        /**
         * 替换StringBuilder[beginIndex,endIndex)的字符为str
         */
        System.out.println(sb.replace(0, 2, "abc"));

        /**
         * 从前往后寻找第一次出现的字符，如果不存在返回-1
         */
        sb=new StringBuilder(str);
        System.out.println(sb.indexOf("i"));

        /**
         * arg: str: 要查找的字符串
         *      fromIndex: 从StringBuilder的哪个下标开始往后查找
         */
        sb=new StringBuilder(str);
        System.out.println(sb.indexOf("i",4));

        /**
         * 从后往前开始查找
         * arg: str: 要查找的字符串
         *      fromIndex: 从StringBuilder的哪个下标开始往前查找
         */
        sb=new StringBuilder(str);
        System.out.println(sb.lastIndexOf("i"));
        System.out.println(sb.lastIndexOf("i",9));


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
