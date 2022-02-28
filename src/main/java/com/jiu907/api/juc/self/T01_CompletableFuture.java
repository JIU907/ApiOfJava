package com.jiu907.api.juc.self;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/28
 */
public class T01_CompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<User> cf = CompletableFuture.supplyAsync(() -> new User());

        /**
         * b: 是指cf的范型表示 -> 就是上诉CompletableFuture.supplyAsync执行完异步任务后的返回值
         * e: 过程中如果报错，则抛出的异常
         *
         * 整体的函数式接口是 Consumer接口，有参无返回值
         */
        cf.whenComplete((b, e) -> System.out.println(b));
    }
}
