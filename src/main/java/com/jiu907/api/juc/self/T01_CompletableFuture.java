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
        CompletableFuture<User> cf = CompletableFuture.supplyAsync(() -> {
            return new User();
        });
        cf.whenComplete((b, e) -> {
            System.out.println(b);
        });
    }
}
