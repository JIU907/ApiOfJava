package com.java.devtool.jdk_study.lang;

import java.io.UnsupportedEncodingException;
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
        // testCodePointApi();

        testCommonApi("HelloWorld");
    }

    public static void testCodePointApi(){
        // 关于代码点的文章: https://blog.csdn.net/qlql489/article/details/82780716
        /**
         * 先理解什么是代码点：
         *     编码字符集中每一个字符都和一个编号对应。那么这个编号就是代码点（Code Point）
         *     那一个char就能表示一个字符吗？ 比如：😃
         *                                并不行，这个表情需要2个char
         *     下面这个例子: codePointCount: 1
         *                 StringLength  : 2
         */
        String codePoint="😃";
        System.out.println("codePointCount: "+codePoint.codePointCount(0,codePoint.length()));    // 方法返回字符串中指定索引处的字符的Unicode值。
        System.out.println("StringLength: " + codePoint.length());

        // 方法返回字符串中指定索引处所处字符的Unicode值。
        System.out.println("codePointAt："+codePoint.codePointAt(0));
        /**
         *  codePointBefore(int index): 字符串中指定索引之前字符的 Unicode 值。
         *      index=0
         *          throw new StringIndexOutOfBoundsException(index);
         *          因为value[0]之前没有值，基本的char是2个字节
         *      index=1；
         *          如果index-1是一个普通字符那么直接返回该Unicode
         *          如果index-1是一个4字节的字符
         *              如果index-1的值处于低代理项，那么index-2是非负数的
         *                  且index-2处于高代理项，则返回该代理项对的增补代码点值可以理解为UTF-32(四字节的表示)
         *              如果index-1的值处于高代理项，直接返回高代理项
         *
         */
        System.out.println((char)"codePoint".codePointBefore(1));
        System.out.println(codePoint.codePointBefore(2));   // UTF-16 0xD83D 0xDE03

        // 返回代码点的统计
        System.out.println("codePointCount:"+codePoint.codePointCount(0,codePoint.length()));

        // 返回此 String 中从给定的 index 处偏移 codePointOffset 个代码点的索引。文本范围内由 index 和 codePointOffset 给定的未配对代理项各计为一个代码点。
        /**
         * codepoint=😃 : 4 Bytes=> a b c d
         * 从value[0]开始往后一个的一个代码点结束位置在d字节上
         * 因为d到a之间隔了2个bytes的距离
         * 所以result=2
         */
        System.out.println("offsetByCodePoints:"+codePoint.offsetByCodePoints(0,1));
    }
    public static void testCommonApi(String str) {
        // private final byte[] value;
        str.length(); // 输出byte[]的长度
        str.isEmpty();// return value.length == 0;
        str.charAt(1);// return value[index];   Maybe Appear Exception "StringIndexOutOfBoundsException"

        /**
         * 从test_getChars中复制指定的字符到dst中
         * srcBegin -- 字符串中要复制的第一个字符的索引。
         * srcEnd   -- 字符串中要复制的最后一个字符之后的索引。
         * dst      -- 目标数组。
         * dstBegin -- 目标数组中的起始偏移量。
         * [srcBegin,srcEnd] -- 复制作用域
         */
        String test_getChars=str;
        char[] dstChar=new char[10];
        test_getChars.getChars(1,8,dstChar,0);;
        System.out.println(dstChar);

        // 异曲同工
        byte[] dstByte=new byte[10];
        test_getChars.getBytes(0,1,dstByte,0);

        try {
            // 根据字符集获取字符串对应的byte array
            byte[] charSetName = str.getBytes("utf-8");
            byte[] charset = str.getBytes(Charset.defaultCharset());
            byte[] noArg = str.getBytes();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // equals 1.判断是否相等 2.判断arg是否为String 3.一次判断value是否相等
        "".equals("");

        // 判断String与String Buffer是否代表着一样的字符序列。如果是String Buffer那么比较时是同步的
        /**
         * {@link String#contentEquals(CharSequence)}
         */
        System.out.println("contentEquals:"+"1".contentEquals(new StringBuffer("2")));

        // 忽略大小写的比较
        System.out.println("equalsIgnoreCase:"+"abc".equalsIgnoreCase("ABC"));

        // 比较字符串
        System.out.println("compareTo:"+"b".compareTo("a"));
        // 忽略大小写的比较，返回的是int值
        System.out.println("compareToIgnoreCase:"+"a".compareToIgnoreCase("A"));

        /**
         * ignoreCase -- 如果为 true，则比较字符时忽略大小写。
         * toffset    -- 此字符串中子区域的起始偏移量。
         * other      -- 字符串参数。
         * ooffset    -- 字符串参数中子区域的起始偏移量。
         * len        -- 要比较的字符数。
         */
        System.out.println("regionMatches:"+"abcde".regionMatches(1,"bbcde",1,1));
        System.out.println("regionMatches ignoreCase:"+"abcde".regionMatches(true,1,"bBcde",1,1));

        /**
         * prefix -- 前缀。
         * toffset -- 字符串中开始查找的位置。
         *
         * startsWith(String str) == startsWith(String str,0)
         */
        System.out.println("startsWith:"+"abc".startsWith("ab",1));
        System.out.println("endsWith:"+"endsWith".endsWith("th")); // 其实调用的是startWith

        /**
         *  for (int i = 0; i < value.length; i++) {
         *                 h = 31 * h + val[i];
         *       }
         */
        System.out.println("a".hashCode());

        /**
         * 字符串中查询字符位置
         * return
         *  -1:不存在
         *  >=0:index
         */
        System.out.println("indexOf:"+"abc".indexOf("q"));
        /**
         * 从字符串的指定位置查询字符位置
         *  fromIndex
         *      <0              :fromIndex=0
         *      >=value.length  :return -1
         *  return
         *      -1: 不存在
         *      >=0: 字符存在，在字符串上的绝对位置，而不是fromIndex的相对地址
         */
        System.out.println("indexOf:"+"abc".indexOf("a",1));
        // 返回字符在字符串中最后一次出现的位置
        System.out.println("lastIndexOf:"+"aabbc".lastIndexOf("a"));
        /**
         *  重点：int i = Math.min(fromIndex, value.length - 1);
         *       for (; i >= 0; i--) {
         *                 if (value[i] == ch) {
         *                     return i;
         *                 }
         *           }
         *        这个fromIndex不是指从字符串的哪个位置开始扫描，而是从fromIndex从前开始扫描
         *
         */
        System.out.println("lastIndexOf:"+"aaabc".lastIndexOf("a",1));

        // substring
        /**
         * substring截取字符串
         *      return: [index,value.length]的字符串
         */
        System.out.println("substring:"+"substring".substring(1));
        /**
         * 2 args的substring
         *  return:[fromIndex,endIndex)的字符串
         */
        System.out.println("substring 2 args:"+"substring".substring(1,2));

        // concat
        /**
         * 拼接两个字符串
         * concat方法用到了{@link String#getBytes(byte[], int, byte)}
         *              : 主要作用就是 将当前字符串从start到end-1位置上的字符复制到字符数组c中，并从c的offset处开始存放
         */
        System.out.println("concat:"+"concat".concat("Test"));

        // 替换字符串，将String中的oldChar替换为newChar
        System.out.println("replace："+"aaabbbccc".replace('b','B'));

        /**
         * arg: 正则表达式
         * return: Boolean=> 字符串是否符合入参中的正则表达式
         */
        System.out.println("matches: "+"matches".matches(".*s"));

        /**
         * 是否包含入参字符串
         * 其实内部是调用了indexOf方法
         */
        System.out.println("contains:"+"contains".contains("con"));

        /**
         * 替换字符串中第一个出现的regex字符为replacement字符
         */
        System.out.println("replaceFirst: "+"aaabbbccc".replaceFirst("x","A"));
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
