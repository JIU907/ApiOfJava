package com.jiu907.api.springboot.config;

import lombok.Data;

/**
 * @Author LeiLiMin
 * @Description: 响应结果集
 * @date: 2022/1/29
 */
@Data
public class ResultResponse<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 携带数据的成功响应
     *
     * @param data 数据
     */
    public static <T> ResultResponse<T> success(T data) {
        ResultResponse<T> result = new ResultResponse<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * 不携带数据的成功响应
     */
    public static ResultResponse success() {
        ResultResponse result = new ResultResponse<>();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 默认的失败响应
     */
    public static ResultResponse fail() {
        ResultResponse result = new ResultResponse<>();
        result.setCode(500);
        result.setMessage("request fail");
        return result;
    }

    /**
     * 自定义失败响应
     */
    public static ResultResponse fail(Integer code, String message) {
        ResultResponse result = new ResultResponse<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
