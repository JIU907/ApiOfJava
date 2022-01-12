package com.jiu907.api.designpatterns.paycenter.paystrategy;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

import javax.net.ssl.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.http.HttpClient;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.Map;

/**
 * @Author LeiLiMin
 * @Description: 苹果支付
 * @date: 2022/1/12
 */
public class ApplePay extends AbstractPayModel {

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    // 沙箱地址
    private static final String url_sandbox = "https://sandbox.itunes.apple.com/verifyReceipt";
    // 正式服地址
    private static final String url_verify = "https://buy.itunes.apple.com/verifyReceipt";

    @Override
    protected void doProcessor() {

    }

    public static String buyAppVerify(String receipt,int type) {
        //环境判断 线上/开发环境用不同的请求链接
        String url = "";
        if(type==0){
            url = url_sandbox; //沙盒测试
        }else{
            url = url_verify; //线上测试
        }

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
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
            System.out.println("苹果服务器异常");
            ex.printStackTrace();
        }
        return null;
    }

    public static String getBASE64(String str) {
        byte[] b = str.getBytes();
        String s = null;
        if (b != null) {
            s=new String(Base64.decodeBase64(b));
        }
        return s;
    }

    public Boolean iosPay(Long priceId, String transactionId, String payload) {
        System.out.println("苹果内购校验开始，交易ID：" + transactionId + " base64校验体：" + payload);
        BasicJsonParser jsonParser = new BasicJsonParser();

        //线上环境验证
        String verifyResult = ApplePay.buyAppVerify(payload, 1);
        if (verifyResult == null) {
            throw new RuntimeException("苹果验证失败，返回数据为空");
        } else {
            System.out.println("线上，苹果平台返回JSON:" + verifyResult);
            Map<String, Object> verifyMap = jsonParser.parseMap(verifyResult);
            String states = verifyMap.get("status").toString();
            //无数据则沙箱环境验证
            if ("21007".equals(states)) {
                verifyResult = ApplePay.buyAppVerify(payload, 0);
                System.out.println("沙盒环境，苹果平台返回JSON:" + verifyResult);
                verifyMap = jsonParser.parseMap(verifyResult);
                states = verifyMap.get("status").toString();
            }
            System.out.println("苹果平台返回值：appleReturn" + verifyMap);
            // 前端所提供的收据是有效的    验证成功
            if (states.equals("0")) {
                return true;
            } else {
                throw new RuntimeException("支付失败，错误码：" + states);
            }
        }
    }

}
