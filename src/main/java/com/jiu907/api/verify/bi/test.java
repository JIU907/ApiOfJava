package com.jiu907.api.verify.bi;

import com.sensorsdata.analytics.javasdk.ISensorsAnalytics;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.consumer.ConcurrentLoggingConsumer;
import com.sensorsdata.analytics.javasdk.consumer.DebugConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/17
 */
public class test {
    public static void main(String[] args) throws InvalidArgumentException {
        /**
         * serverUrl: 数据接收地址数据是否入库，
         * writeData: true:表示数据入库；false:表示数据不入库（只展示不保存）
         */
        String serverUrl = "";
        // ISensorsAnalytics sa = new SensorsAnalytics(new DebugConsumer(serverUrl,true));

        /**
         * 生产推荐：
         */
        String filePath="/Users/leilimin/SensorsBi/log/";
        ConcurrentLoggingConsumer consumer = new ConcurrentLoggingConsumer(filePath);
        SensorsAnalytics sa = new SensorsAnalytics(consumer);

        // 1.匿名ID
        String distinctId = "ABCDEF123456789";
        EventRecord lookRecord = EventRecord.builder().setDistinctId(distinctId).isLoginId(Boolean.FALSE)
                .setEventName("ViewProduct")
                .addProperty("$time", new Date())  // '$time' 属性是系统预置属性，表示事件发生的时间，如果不填入该属性，则默认使用系统当前时间
                // '$ip' 属性是系统预置属性，如果服务端中能获取用户 IP 地址，并填入该属性，神策分析会自动根据 IP 地址解析用户的省份、城市信息
                .addProperty("$ip", "123.123.123.123")
                .addProperty("ProductId", "123456")
                .build();
        // 记录用户浏览商品事件
        sa.track(lookRecord);

        // 订单中的商品 ID 列表
        List<String> productIdList = new ArrayList<String>();
        productIdList.add("123456");
        productIdList.add("234567");
        productIdList.add("345678");
        EventRecord lookRecord2 = EventRecord.builder().setDistinctId(distinctId).isLoginId(Boolean.FALSE)
                .setEventName("PaidOrder")
                // '$ip' 属性是系统预置属性，如果服务端中能获取用户 IP 地址，并填入该属性，神策分析会自动根据 IP 地址解析用户的省份、城市信息
                .addProperty("$ip", "123.123.123.123")
                .addProperty("OrderId", "123456")
                .addProperty("ProductIdList", productIdList)
                .build();
        // 记录用户订单付款事件
        sa.track(lookRecord2);
        sa.flush();
    }
}
