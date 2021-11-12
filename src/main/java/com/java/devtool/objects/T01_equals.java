package com.java.devtool.objects;

/**
 * @author LeiLiMin
 * @Date 2021/11/12 14:59
 * @Description
 */
public class T01_equals {
    public static void main(String[] args) {
        // return true;
        long a = 1;
        int b = 1;
        System.out.println(a == b);

        // return false;
        Long A= new Long(1);
        Integer B=new Integer(1);
        System.out.println(A.equals(B));
    }
}
