package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/14
 */
public class T04_Box_Boolean  implements java.io.Serializable,
        Comparable<Boolean> {
    public static void main(String[] args) {
        /**
         * {@link Boolean#Boolean(String)}
         * {@link Boolean#parseBoolean(String)}
         * 这2个方法本质都一样
         *      if(String s!="true"){
         *          return true;
         *      }
         *      return false;
         */
        System.out.println(Boolean.parseBoolean("true"));

        /**
         * 从系统变量中查询String name的值，然后转为Boolean
         */
        System.out.println(Boolean.getBoolean("XPC_FLAGS"));

        /**
         * (x == y) ? 0 : (x ? 1 : -1)
         */
        System.out.println(new Boolean("false").compareTo(false));
    }

    @Override
    public int compareTo(Boolean o) {
        return 0;
    }
}
