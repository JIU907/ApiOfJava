package com.jiu907.api.utils.dingrobot.service;

/**
 * @Author LeiLiMin
 * @Description: 钉钉机器人-顶级接口
 * @date: 2022/3/1
 */
public interface DingRobotService {
    /**
     * 通知机器人发消息
     */
    String sendMsg(String msg);
}
