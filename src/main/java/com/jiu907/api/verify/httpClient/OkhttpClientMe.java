package com.jiu907.api.verify.httpClient;

import com.google.gson.Gson;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Author LeiLiMin
 * @Description: OkHttpClient
 * @date: 2022/1/14
 */
public class OkhttpClientMe {
    public static void main(String[] args) {

    }

    public String getAsync(String url, Map<String, String> paramMap,String json) {

        // 构建url参数
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (Objects.nonNull(paramMap)) {
            paramMap.forEach(urlBuilder::addQueryParameter);
        }
        // 构建请求
        Request.Builder requestBuilder = new Request.Builder();
        // 请求体，如果json为空，则请求体也为空
        RequestBody requestBody = Objects.nonNull(null) ? RequestBody.create(gson.toJson(json), MediaType.parse("application/json; charset=utf-8")) : null;
        Request get = requestBuilder
                .method("GET", requestBody)
                .url(urlBuilder.build())
                .build();

        // 执行方法，设置回调
        Call call = httpClient.newCall(get);
        // TODO: 后续增加测试
        return null;
    }


    class FunctionCallBack<T,R> implements Callback{
        private Function<T,R> function;

        public FunctionCallBack(Function<T, R> function) {
            this.function = function;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {

        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        }
    }














    private Gson gson = new Gson();
    private final OkHttpClient httpClient;
    // 最大连接数
    private static final int MAX_CONNECTION = 100;
    // 每个route能使用的最大连接数，一般和MAX_CONNECTION取值一样
    private static final int MAX_CONCURRENT_CONNECTIONS = 100;
    // 建立连接的超时时间，单位毫秒
    private static final int CONNECTION_TIME_OUT = 3000;
    // 请求超时时间，单位毫秒
    private static final int REQUEST_TIME_OUT = 3000;
    // 长连接的时间
    private static final long keepAliveDuration = 5;

    public OkhttpClientMe() {
        ConnectionPool connectionPool = new ConnectionPool(MAX_CONNECTION, keepAliveDuration, TimeUnit.MINUTES);
        httpClient = new OkHttpClient.Builder()
                .readTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .build();
    }
}
