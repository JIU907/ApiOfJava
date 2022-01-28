package com.jiu907.api.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LeiLiMin
 * @Description: 支付请求Dto
 * @date: 2022/1/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDto {
    /**
     * 支付方式
     */
    private String payModel;

    /**
     * 三方验签信息
     */
    private String extra;
}
