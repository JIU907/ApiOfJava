package com.jiu907.api.verify.enumtest;

import lombok.Data;

/**
 * @Author LeiLiMin
 * @Description: 测试枚举类的set方法
 * @date: 2022/2/18
 */
public enum T01_InvokeSetEnum {
    SPIN_CONSUME(0, "spin消耗"),

    /**
     * 基础Spin结算
     */
    BASE_SPIN(1, "base spin incr"),

    /**
     * Bonus结算
     */
    BONUS_REWARD(2, "bonus reward"),

    /**
     * freeSpin结算
     */
    FREE_SPIN(3, "free Spin reward");

    private int typeCode;
    private String reason;

    private String param1;
    private String param2;
    private String param3;


    T01_InvokeSetEnum(int typeCode, String reason) {
        this.typeCode = typeCode;
        this.reason = reason;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }
}
class test{
    public static void main(String[] args) {
        // get出set之后的值
        T01_InvokeSetEnum.SPIN_CONSUME.setParam1("111");
        System.out.println(T01_InvokeSetEnum.SPIN_CONSUME.getParam1());
        T01_InvokeSetEnum.SPIN_CONSUME.setParam1("222");

    }
}
