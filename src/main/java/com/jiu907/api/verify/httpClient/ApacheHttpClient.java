package com.jiu907.api.verify.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/14
 */
public class ApacheHttpClient {
    // httpClient
    public static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws IOException {
        // 1.创建请求
        HttpGet httpGet = new HttpGet("http://1.15.151.138:8080/payController/pay1");

        // 2.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 3.获取状态码
        int statusCode = response.getStatusLine().getStatusCode();

        // 4.请求成功
        if (statusCode == 200) {

            // 5.获取返回结果
            HttpEntity httpEntity = response.getEntity();
            StringBuilder entityStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                entityStringBuilder.append(line);
            }
            //利用从HttpEntity中得到的String生成JsonObject
            System.out.println(entityStringBuilder.toString());
        }

    }
}
