package com.enter.repair2.utils;

import com.alibaba.fastjson.JSONObject;
import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;

/**
 * @className WeChatAccessTokenUtil
 * @auther Liquid
 * @description
 * @date 2018/12/21
 */
@Slf4j
public class WeChatAccessTokenUtil {

    public static String getAccessToken(String appId, String secret) throws CheckedException {
        StringBuilder stringBuilder = new StringBuilder();
        String params = stringBuilder.append("grant_type=")
                .append(RepairAppConfig.GRANT_TYPE)
                .append("&appid=").append(appId)
                .append("&secret=").append(secret).toString();
        String accessTokenInfo = HttpRequestUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
        Object access_token = JSONObject.parseObject(accessTokenInfo).get("access_token");
        String result = "";
        if (access_token != null) {
            result = access_token.toString();
        } else {
            String errmsg = JSONObject.parseObject(accessTokenInfo).get("errmsg").toString();
            throw new CheckedException("调用微信获取调用Token接口失败 " + errmsg);
        }
        String expiresIn = JSONObject.parseObject(accessTokenInfo).get("expires_in").toString();
        return result;
    }

    public static String getEnterpriseWeChatAccessToken(String corpid, String corpsecret) throws CheckedException {
        StringBuilder stringBuilder = new StringBuilder();
        String params = stringBuilder.append("corpid=")
                .append(corpid)
                .append("&corpsecret=")
                .append(corpsecret)
                .toString();
        String accessTokenInfo = HttpRequestUtils.sendGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken", params);
        Object access_token = JSONObject.parseObject(accessTokenInfo).get("access_token");
        String result = "";
        if (access_token != null) {
            result = access_token.toString();
        } else {
            String errmsg = JSONObject.parseObject(accessTokenInfo).get("errmsg").toString();
            throw new CheckedException("调用企业微信获取调用Token接口失败 " + errmsg);
        }
        String expiresIn = JSONObject.parseObject(accessTokenInfo).get("expires_in").toString();
        return result;
    }

}
