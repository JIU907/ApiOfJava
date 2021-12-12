package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description: ClassLoader.class学习
 * @date: 2021/12/11
 */
public class T03_ClassLoader {
    /**
     * 1.ClassLoader负责对一个类进行加载
     * 2.给定一个类的名称，一个classLoader应该去尝试确定或者生产一个(关于这个类的定义信息)数据
     * 3.一个典型的策略就是根据文件系统中的一个名称去读取该"类文件"
     *
     * <p>每个Class对象包含一个ClassLoader去定义该Class(ClassLoader通过Class#getClassLoader()获取)</p>
     *
     * <p> 数组对象不通过class loader进行创建，但是必须自动的在java运行时被创建
     *     调用数组对象的(Class#getClassLoader()),返回的是其元素的ClassLoader
     *     如果其元素是原始的数据类型(primitive type)，这个数组的Class对象是没有ClassLoader的
     * </p>
     *
     * <p>
     *     应用程序实现Class Loader子类的方式去扩展JVM动态加载类的方式
     * </p>
     *
     * <p>
     *     Class Loder使用双亲委派机制去搜寻class文件或者资源
     *     每一个Class Loader实例都有一个关联的parent Class Loader
     *     当请求寻找一个Class文件或者资源，这个Class Loader将首先委托它的parent Class Loader去寻找
     *     JVM固定的类加载器是Boot Strap Class Loader，它没有parent Class Loader，但是它可能作为一个parent Class Loader
     *     为其他的Class Loader实例提供服务
     * </p>
     */
}
