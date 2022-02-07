package com.jiu907.api.verify.sensorsbi.utils;

import org.codehaus.plexus.util.StringUtils;

import java.util.UUID;

/**
 * @Author LeiLiMin
 * @Description: TraceId 工具类
 * @date: 2022/1/19
 */
public class TraceUtil {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public static String readTraceId(){

        return threadLocal.get();
    }
    public static void writeTraceId(String traceId){
        if(StringUtils.isBlank(traceId)){
            throw new RuntimeException("traceId is blank");
        }
        threadLocal.set(traceId);
    }

    public static void delTraceId(){
        threadLocal.remove();
    }

    public static String createTraceId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
