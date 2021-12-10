package com.java.devtool.jdk_study.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

@Data
@AllArgsConstructor
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
        System.out.println(testClassType[].class);

        /**
         * 1.调用无参构造方法创建一个对象，这个类会被初始化，前提是它之前并未初始化
         * 2.这个方法传播一些异常通过 空参构造器，包括一个检查异常，绕过了编译器的编译器异常检查
         * 这个方法包装了任何由构造器引起的异常
         */
        testClassType test1 = testClassType.class.newInstance();
//     day2   =====================================================================================
        /**
         * 1.Class#isInstance(Object obj)     ：判断的是 本类及子类，如果obj是父类则返回false
         * 2.Class#isAssignableFrom(Class obj)：判断的是 本类及子类，如果obj是父类则返回false
         * 3.Class#getClassLoader();          ：获取类加载器，有些情况下如果加载器为bootstrap class loader的话返回null
         * 5.Class#getSuperclass()            ：获取直接父类，不带泛型
         * 6.Class#getGenericSuperclass()     ：获取直接父类，带泛型
         *
         * 7.Class#getDeclaringClass()        ：可以通过Method使用该方法获取方法所属的类
         *
         * 8.Class#中还包含类一些是否为内部类，是否为XXX类的方法，可以自行按照需查看
         */
        // 判断该对象是否为该类的实例  == instanceof ？？
        /**
         * 其实不等价
         * Class#isInstance(Object obj)：判断的是 本类及子类，如果obj是父类则返回false
         * instanceof则相反：判断的是本类及父类，如果obj是子类，则为false
         */
        Class<testClassType> testClass = testClassType.class;
        System.out.println(testClass.isInstance(new subClassType()) +"\t"+(test1 instanceof testClassType));
        System.out.println(testClass.isAssignableFrom(testClassType.class));    // 于Class#isInstance(Object obj)等价，但是入参是Class

        System.out.println(Integer.TYPE.isPrimitive());                         // 判断Class是否为基础数据类型

        System.out.println(AllArgsConstructor.class.isSynthetic());             // 那些由Java编译器引入，并且在源代码中没有相应构造方法的结构体则被认为是合成的，（被引入的代码）除了默认的构造器、类初始化方法（<init>();）以及枚举类型的values/valueOf方法。

        // 获取类加载器，有些情况下如果加载器为bootstrap class loader的话返回null
        System.out.println(testClassType.class.getClassLoader());
        System.out.println(T02_Class.class.getClassLoader().getClass().getClassLoader());


        TypeVariable<Class<testClassType>>[] typeParameters = testClassType.class.getTypeParameters();  // 返回一个长度为0的该Class的数组
        System.out.println(typeParameters.length);

        // 返回直接父类 的class和Type
        // Generic的目的在于可以携带泛形信息
        System.out.println(subClassType.class.getSuperclass());
        Type genericSuperclass = subClassType.class.getGenericSuperclass();
        System.out.println(genericSuperclass);

        System.out.println();
        for (Method declaredMethod : T02_Class.class.getDeclaredMethods()) {
            // 获取Method所被声明的类
            System.out.println(declaredMethod.getDeclaringClass());
        }


        // 于geName的区别在于
        // getName是Jvm内的表示，getCanonicalName更容易被理解的表示
        System.out.println( T02_Class.class.getCanonicalName());
//        day3   =====================================================================================
        System.out.println("day3");
        // 获取类中包含多少个public的内部类
        subClassType.testClasses();
        // 获取这个Java类的详细介绍信息
        System.out.println(testClassType.class.getProtectionDomain());
    }
}
interface xxx{}
class testClassType{
    static final Class<T02_Class> a=T02_Class.class;
    public testClassType(/*String hhh*/){
         class inMethodClass{
            int a=1;
        }
        inMethodClass inMethodClass = new inMethodClass();
         // 判断当前类是否是Local类，并且不是匿名内部类
        System.out.println("xxx:"+inMethodClass.getClass().isLocalClass());
    }
    static {
        System.out.println("test 被初始化啦");
    }
}
class subClassType extends testClassType{
    int[] a;
    public static void testClasses(){
        Class<?>[] classes = subClassType.class.getClasses();
        System.out.println(classes.length);
    }
    public class InnerClass{}
}