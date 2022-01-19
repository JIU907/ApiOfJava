package com.jiu907.api.verify.bi;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/19
 */
public interface TraceConstants {
    /**
     * key : traceId传递时的key
     * 链路追踪传递的key
     * 用于ms，rpc，kafka, rockmq 中
     * 不与API_HTTP_TRACE_ID_HEADER_KEY用同一个原因是因为当前运维配置符号"_"被nginx / ingress是非法字符
     */
    String TRACE_ID_KEY = "traceId";
    /**
     * 用于链路追踪传递的key
     * 1.api客户端写入http header中的的trace id的key
     * 2.响应客户端时写入response header中的key
     */
    String API_HTTP_TRACE_ID_HEADER_KEY = "trace_id";
    /**
     * 埋点key
     */
    String TRACE_ID_SENSOR_KEY = "trace_id";

    String KEY_EVENT_NAME="event_name";
}
