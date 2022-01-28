package com.jiu907.api.springboot.utils.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;


/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/22
 */
public class RedisClientService {
    private String host;

    public static Integer port;

    /**
     * redis连接权限
     */
    public static String auth;

    public static Jedis jedisClient;

    public RedisClientService(String host, Integer port, String auth) {
        if (jedisClient == null) {
            synchronized (this) {
                if (jedisClient == null) {
                    this.host = host;
                    this.port = port;
                    this.auth = auth;
                    jedisClient = new Jedis(this.host, this.port);
                    jedisClient.auth(this.auth);
                }
            }
        }
    }

    /**
     * 没有任何参数的Lua脚本
     *
     * @param script: Lua脚本
     */
    public String evalNothing(String script) {
        Object eval = jedisClient.eval(script);
        return eval.toString();
    }

    /**
     * 没有任何参数的Lua脚本
     *
     * @param script: Lua脚本
     */
    public String evalNoArgs(String script, List<String> keys) {
        String[] keysArr = keys.toArray(new String[keys.size()]);
        Object eval = jedisClient.eval(script, keysArr.length, keysArr);
        return eval.toString();
    }
}
