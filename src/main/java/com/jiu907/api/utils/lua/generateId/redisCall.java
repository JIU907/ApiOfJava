package com.jiu907.api.utils.lua.generateId;

import com.jiu907.api.utils.ioutil.FileStream;
import com.jiu907.api.utils.redisclient.RedisClientService;

import java.util.ArrayList;


/**
 * @Author LeiLiMin
 * @Description: 测试Lua脚本
 * @date: 2022/1/21
 */
public class redisCall {
    /**
     * 特别的：
     * 集群模式下，Redis 使用 hash tag 插入到同一个哈希槽
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1.获取客户端
        RedisClientService redisClient = new RedisClientService("1.15.151.138", 6379, "123456");

        // 2.获取Lua脚本
        String script = FileStream.readData("/Users/leilimin/IDEA-MySpace/ApiOfJava/src/main/java/com/jiu907/api/utils/lua/refreshPool/RefreshPool.lua");

        // 查看结果校验
        ArrayList<String> keys = new ArrayList<>();
        keys.add("jackpotPool");
        ArrayList<String> arg = new ArrayList<>();
        arg.add(String.valueOf(3600));
        System.out.println(redisClient.evalArgs(script, keys, arg));
    }
}
