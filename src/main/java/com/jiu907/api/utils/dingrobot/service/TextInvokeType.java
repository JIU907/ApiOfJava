package com.jiu907.api.utils.dingrobot.service;


/**
 * @Author LeiLiMin
 * @Description: 文本类型通知
 * @date: 2022/3/1
 */
public class TextInvokeType implements DingRobotService {
    private String webhook;
    private Boolean onOff;

    public TextInvokeType(String webhook, Boolean onOff) {
        this.webhook = webhook;
        this.onOff = onOff;
    }

    @Override
    public String sendMsg(String msg) {
        return null;
    }
}
