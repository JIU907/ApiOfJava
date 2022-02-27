package com.jiu907.api;

import com.jiu907.api.jdk8.stream.model.User;

import java.lang.reflect.Method;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/15
 */
public class Test {
    private static User user = new User(1, false, "lewis", new User.Address("1", "2", "3"));

    public static void main(String[] args) throws Exception {
        /**
         * 测试目的：
         *      发射是否能够获取protected修饰的方法
         */
        Class<TestReflect> testReflectClass = TestReflect.class;
        Method[] declaredMethods = testReflectClass.getDeclaredMethods();
        Method[] methods = testReflectClass.getMethods();

        // new Thread(new Runnable() {
        //     @SneakyThrows
        //     @Override
        //     public void run() {
        //         System.out.println("T1 come in");
        //         synchronized (user) {
        //             user.setSex(true);
        //             Thread.sleep(5000);
        //             System.out.println("T1 come over");
        //
        //         }
        //     }
        // }, "T1").start();
        // Thread.sleep(1000);
        // new Thread(new Runnable()   提供 {
        //     @Override
        //     public void run() {
        //         synchronized (user) {
        //             System.out.println("T2 come in");
        //             System.out.println(user.getSex());
        //             user.setSex(false);
        //             System.out.println(user.getSex());
        //         }
        //     }
        // }, "T2").start();
    }
}

class TestReflect {
    protected void testProtected() {
    }
}