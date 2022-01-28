package com.jiu907.api.jdk.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T01_Object{
    volatile static int variable=18;
    int age;
    Integer a;
    public static void main(String[] args) throws Exception{
        T01_Object t1=new T01_Object();
        /**
         * t1.clone();
         *      1.是对 对象的浅拷贝，内层字段还是直接赋值地址引用
         *      2.该方法被protected修饰(只有基类所在的包及其子类才能调用)https://www.runoob.com/w3cnote/java-protected-keyword-detailed-explanation.html
         * t1.getClass();
         *      1.返回Class
         *
         */
        // 本质是在比较地址:  return (this == obj);
        t1.equals(new Object());
        // return getClass().getName() + "@" + Integer.toHexString(hashCode());
        t1.toString();
        // 在该对象的wait set中随机唤醒一个线程。此方法只能被持有该对象监视器的线程调用
        /**
         * （ 一个线程成为该线程的所有者，有三种方式）
         *    调用同步方法
         *    执行同步方法体
         *    执行该类的同步静态方法
         */
        t1.notify();
        // 唤醒所有正在等待该对象锁的线程，被唤醒的线程是去尝试获取锁，而不是继续工作
        t1.notifyAll();

        /**
         * 随机的唤醒一个正在等待该对象锁的线程
         * 如何唤醒:
         *  1.其他线程，调用notify()
         *  2.其他线程，调用notifyAll()
         *  3.wait(long)，所需等待的时间片到了
         *  4.调用interrupt()，以异抛出InterruptedException的形式被唤醒
         * 虚假唤醒:
         *  1.等待应始终在循环中发生。因为使用if不可避免的会顺序执行下一步的代码
         *
         * 释放锁，并处于对象锁的wait set中
         */
        t1.wait();
        t1.wait(1l);
        t1.wait(1l,1);

        // GC相关的知识
        // t1.finalize();

    }

    public static void testMethod_notify(){
        final Object sum=new Object();

        new Thread(new Runnable() {
            @Override
            public void  run() {
                try {
                    synchronized (sum) {
                        System.out.println("t1 get lock");
                        while(variable!=0){
                            Thread.sleep(2000);
                            sum.wait();
                        }
                        System.out.println("t1 over");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void  run() {
                try {
                    synchronized (sum) {
                        System.out.println("t2 get lock");
                        variable=0;
                        Thread.sleep(2000);
                        sum.notify();
                        System.out.println("t2 use notify");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public static void testMethod_Clone(){
        try {
            T01_Object test=new T01_Object(1,1256);
            T01_Object clone = (T01_Object)test.clone();
            // clone出的对象是不一样的
            System.out.println(test==clone);
            // 但是引用类型的Field还是直接赋值引用
            System.out.println(test.a == clone.a);
        } catch (CloneNotSupportedException e) {}
    }
}

