package com.jiu907.api.designpatterns.paycenter.model;

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
     * 0.AliPay
     */
    private Integer payModel;
}
