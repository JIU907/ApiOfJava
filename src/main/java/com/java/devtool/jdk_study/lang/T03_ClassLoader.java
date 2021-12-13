package com.java.devtool.jdk_study.lang;

/**
 * @Author LeiLiMin
 * @Description: ClassLoader.class学习
 * @date: 2021/12/11
 */
public class T03_ClassLoader extends ClassLoader{
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
    public static void main(String[] args) {
        testClassLoader_DiedLock();
    }
    public static void testClassLoader_DiedLock(){
        // 此处掩饰了类加载的死锁问题
        // 支持并发得类加载器称为并行类加载器，他们需要在自己初始化时调用#registerAsParallelCapable()进行注册
        /**
         * Class Loader在进行初始化时默认的会设置为parallel class loader {@link ClassLoader#registerAsParallelCapable()}
         * 那么并行不并行的有什么区别吗？有的
         * 类加载入口观察: {@link ClassLoader#loadClass(String, boolean)}
         *               {@link ClassLoader#getClassLoadingLock(String)},
         *      1.并行时=>会将传入的name作为key，new Object()作为value，并且该value作为锁
         *               也就是ClassLoader可以多线程去初始化不同的类
         *      2.串行时=>会将该Class Loader作为lock，也就会导致如果多线程用该ClassLoader时会发生阻塞的情况
         */
        /**
         * {@link java.lang.ClassLoader.ParallelLoaders}：并发容器锁的实现
         * 这个类决定了{@link ClassLoader.parallelLockMap}是否为空
         */
        new Thread(()->new A()).start();
        new Thread(()->new B()).start();
    }
}

class A{
    static{
        System.out.println("Class A init");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new B();
    }
}
class B{
    static{
        System.out.println("Class B init");
        new A();
    }

}
