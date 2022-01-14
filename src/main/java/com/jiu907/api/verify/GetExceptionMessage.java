package com.jiu907.api.verify;

/**
 * @Author LeiLiMin
 * @Description: 获取异常的Message
 * @date: 2022/1/14
 */
public class GetExceptionMessage {

    /**
     * Spring全局异常
     */
    public static void main(String[] args) {
        try {
            throwException();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwException() {
        throw new RuntimeException("throw my exception");
    }
}
