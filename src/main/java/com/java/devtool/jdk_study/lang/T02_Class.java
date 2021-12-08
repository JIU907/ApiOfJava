package com.java.devtool.jdk_study.lang;

public class T02_Class {
    /**
     * enum is a kind of class
     * annotation is a kind of interface
     *
     * (Class 是没有共有的构造函数，它的构造发生在JVM调用类装载器加载该Class)
     *
     * class 的构造器的入参是一个classLoader
     *
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        System.out.println(Object.class.toString());
        Class<T02_Class> type = T02_Class.class;
        System.out.println(type.getName());         //  全路径名称
        System.out.println(type.getSimpleName());   //  类名称
        System.out.println(type.toString());        // class||interface  getName()
        System.out.println(type.toGenericString()); // 携带访问修饰符输出

        /**
         * Class.forName("Foo") == > Class.forName("Foo", true, this.getClass().getClassLoader())
         * 1.加载的类会被load，初始化
         * 2.如果第二个参数为false,那么也不会被初始化
         * 3.如果第三个参数为null,那么会调用bootstrap class loader 进行加载
         * 4.如果是 test[].class，那么test会被load，但不会被初始化
         */
//        Class.forName("com.java.devtool.jdk_study.lang");
        System.out.println(test[].class);

        /**
         * 1.调用无参构造方法创建一个对象，这个类会被初始化，前提是它之前并未初始化
         * 2.这个方法传播一些异常通过 空参构造器，包括一个检查异常，绕过了编译器的编译器异常检查
         * 这个方法包装了任何由构造器引起的异常
         */
        test test = test.class.newInstance();
    }
}
class test{
    public test(String hhh){

    }
    static {
        System.out.println("test 被初始化啦");
    }
}
