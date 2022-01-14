package com.jiu907.api.verify;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/14
 */
public class Optional {
    public static void main(String[] args) {
        String str = null;
        // allow to store value of null
        java.util.Optional<String> str2 = java.util.Optional.ofNullable(str);

        // when store null。throw NullPointException
        java.util.Optional<String> str1 = java.util.Optional.of(str);



    }
}
