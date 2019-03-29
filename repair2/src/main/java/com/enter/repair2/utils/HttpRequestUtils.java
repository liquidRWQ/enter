package com.enter.repair2.utils;

import com.enter.repair2.exception.CheckedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Liquid
 * @类名： HttpRequestUtil
 * @描述： 发送HTTP请求的工具类
 * @date 2018/12/28
 */
public class HttpRequestUtils {

    /**
     * @param url   发送请求的地址
     * @param param 发送请求的参数（以key=value的形式，并通过“&”进行连接）
     * @return java.lang.String 返回结果
     * @throws CheckedException 检查异常
     * @描述： 发送HTTP协议GET请求
     * @author Liquid
     * @date 2018/12/26
     */
    public static String sendGet(String url, String param) throws CheckedException {

        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        try {
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            throw new CheckedException("发送GET请求异常:" + e.toString());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new CheckedException("发送GET请求异常:" + e.toString());
            }
        }
        return result;
    }

    /**
     * @param url   发送请求的地址
     * @param param 发送请求的参数（一个json对象字符串）
     * @return java.lang.String 返回结果
     * @throws CheckedException 检查异常
     * @描述： 发送HTTP协议POST请求
     * @author Liquid
     * @date 2018/12/26
     */
    public static String sendPost(String url, String param) throws CheckedException {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new CheckedException("发送POST请求异常:" + e.toString());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new CheckedException("发送POST请求异常:" + e.toString());
            }
            if (out != null) {
                out.close();
            }
        }
        return result;
    }
}


