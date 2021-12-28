package com.java.devtool.jdk_study.lang;


/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2021/12/27
 */
public class T02_Class_enhanceInstanceof {
    // instanceof
    public static void main(String[] args) {
        Super supers=new Sub();
        System.out.println("======isAssignableFrom======");
        // 总结来说就是，这个对象 instance of 本类及其子类才为true
        System.out.println(supers.getClass().isAssignableFrom(Super.class));
        System.out.println(supers.getClass().isAssignableFrom(Sub.class));
        System.out.println(supers.getClass().isAssignableFrom(subSub.class));

        System.out.println("======instanceof======");
        // 总结来说就是，这个对象 instance of 本类及其父类才为true
        System.out.println(supers instanceof Super);
        System.out.println(supers instanceof Sub);
        System.out.println(supers instanceof subSub);

    }

}
class Super{

}
class Sub extends Super{

}
class subSub extends Sub{

}