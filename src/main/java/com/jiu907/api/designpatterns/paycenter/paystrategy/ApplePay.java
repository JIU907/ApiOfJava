package com.jiu907.api.designpatterns.paycenter.paystrategy;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.Map;

/**
 * @Author LeiLiMin
 * @Description: 苹果支付
 * @date: 2022/1/12
 */
@Component(value = "applePay")
public class ApplePay extends AbstractPayModel {

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    // 沙箱地址
    @Value("${apple.verifyReceiptSandboxURL}")
    private String url_sandbox;

    // 正式服地址
    @Value("${apple.verifyReceiptURL}")
    private String url_verify;


    @Override
    protected void doProcessor() {

    }

    public String buyAppVerify(String receipt, int type) {
        //环境判断 线上/开发环境用不同的请求链接
        String url = "";
        if (type == 0) {
            url = url_sandbox; //沙盒测试
        } else {
            url = url_verify; //线上测试
        }

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "text/json");
            conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            BufferedOutputStream hurlBufOus = new BufferedOutputStream(conn.getOutputStream());

            String str = String.format(Locale.CHINA, "{\"receipt-data\":\"" + receipt + "\"}");//拼成固定的格式传给平台
            hurlBufOus.write(str.getBytes());
            hurlBufOus.flush();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Apple Pay Request Fail");
        }
    }


    public Boolean iosPay(String payload) {
        BasicJsonParser jsonParser = new BasicJsonParser();

        // 1.线上环境验证
        String verifyResult = buyAppVerify(payload, 1);
        if (verifyResult == null) {
            throw new RuntimeException("Apple Pay,Return Null Json");
        }

        Map<String, Object> verifyMap = jsonParser.parseMap(verifyResult);
        String states = verifyMap.get("status").toString();
        // 2.沙箱环境验证
        if ("21007".equals(states)) {
            verifyResult = buyAppVerify(payload, 0);
            verifyMap = jsonParser.parseMap(verifyResult);
            states = verifyMap.get("status").toString();
        }

        // 3.结果校验
        if (states.equals("0")) {
            // 业务逻辑校验
            verifyMap = (Map<String, Object>) verifyMap.get("receipt");
            return true;
        } else {
            throw new RuntimeException("支付失败，错误码：" + states);
        }

    }

}
