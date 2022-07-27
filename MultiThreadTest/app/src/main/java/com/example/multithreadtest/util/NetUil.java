package com.example.multithreadtest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * [类的说明]
 */
public class NetUil {

    public static String BASE_URL = "h";


    public static String doGet(String url) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
//            新建连接
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Charset", "utf-8");
            urlConnection.connect();
//            从连接里面获取输入流（二进制）
            InputStream inputStream = urlConnection.getInputStream();
//            将输入流包装转换为BufferedReader（易懂的文本）
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//            从BufferedReader中一行行读取字符，用StringBuilder接收
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if (stringBuilder.length() == 0) {
                return null;
            }
//          StringBuilder拼接成最终的字符串文本
            bookJSONString = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookJSONString;
    }
}
