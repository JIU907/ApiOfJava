package com.jiu907.api.utils.generateId;

import com.jiu907.api.utils.io.FileStream;
import com.jiu907.api.utils.redisclient.RedisClientService;

import java.util.ArrayList;


/**
 * @Author LeiLiMin
 * @Description: 测试Lua脚本
 * @date: 2022/1/21
 */
public class redisCall {
    public static void main(String[] args) {
        // 1.获取客户端
        RedisClientService redisClient = new RedisClientService("1.15.151.138",6379,"123456");

        // 2.获取Lua脚本
        String script = FileStream.readData("/Users/leilimin/IDEA-MySpace/ApiOfJava/src/main/java/com/jiu907/api/utils/generateId/DelKey.lua");

        // 查看结果校验
        ArrayList<String> strings = new ArrayList<>();
        strings.add("k1");
        strings.add("k2");
        strings.add("k3");
        System.out.println(redisClient.evalNoArgs(script,strings));
    }
}
