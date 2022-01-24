package com.jiu907.api.verify.bi;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author LeiLiMin
 * @Description: 获取Aliyun的权限Token
 * @date: 2022/1/24
 */
public class AliyunService {
    // 设置编码集
    private static final String ENCODING = "UTF-8";

    // 日期格式化
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * 构造规范化请求字符串
     */
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }

    /**
     * 指定格式化日期
     * @param date
     * @return
     */
    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }


}
