package com.enter.repair2.utils;

import com.alibaba.fastjson.JSONObject;
import com.enter.repair2.DTO.WeChatLoginDTO;
import com.enter.repair2.entity.User;
import com.enter.repair2.exception.UnCheckedException;
import lombok.extern.slf4j.Slf4j;

/**
 * 类名： CodeUtil
 *
 * @author Liquid
 * <p>
 * 描述：微信授权登录工具类
 * @date 2018/12/26
 */
@Slf4j
public class CodeUtil {
    /**
     * 微信小程序Id
     */
    private static final String WX_APPID = "wx2e7304608fad59e6";
    /**
     * 微信小程序密钥
     */
    private static final String WX_SECRET = "a252d489ba3c249c73dc4a7b947bd64e";
    /**
     * 授权类型
     */
    private static final String GRANT_TYPE = "authorization_code";

    /**
     * @param weChatLoginDTO 描述：调用微信的API获取用户的openId
     * @return User对象
     * @author Liquid
     * @date 2018/12/26
     */
    public static User getUserOpenIdByWeChatLoginDTO(WeChatLoginDTO weChatLoginDTO) throws Exception {

        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        String code = weChatLoginDTO.getCode();
        String encryptedData = weChatLoginDTO.getEncryptedData();
        String iv = weChatLoginDTO.getIv();
        StringBuilder stringBuilder = new StringBuilder();
        String params = stringBuilder.append("appid=").append(WX_APPID)
                .append("&secret=").append(WX_SECRET)
                .append("&js_code=").append(code)
                .append("&grant_type=").append(GRANT_TYPE)
                .toString();
        String sessionKey = getSessionKey(params);
        String encrypted = AesUtils.decrypt(encryptedData, sessionKey, iv);
        User user = getUser(encrypted, sessionKey);
        return user;

    }

    /**
     * @param params 拼装好了的参数
     *               <p>
     *               描述：调用微信aio获取sessionKey
     * @return AES解密用的密钥
     * @author Liquid
     * @date 2018/12/26
     */
    private static String getSessionKey(String params) throws Exception {

        String data = HttpRequestUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        log.info("method:" + Thread.currentThread().getStackTrace()[1].getClassName() + "  data:" + data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        if (jsonObject.get("session_key") == null) {
            throw new UnCheckedException("调用微信登录接口失败");
        }
        String sessionKey = jsonObject.get("session_key").toString();
        return sessionKey;
    }

    /**
     * @param encrypted,sessionKey 描述：解密数据并封装成User对象
     * @return User对象
     * @author Liquid
     * @date 2018/12/26
     */
    private static User getUser(String encrypted, String sessionKey) {
        JSONObject userInfoJSON = JsonUtils.toJSONObject(encrypted);
        System.out.println("userInfoJSON = " + userInfoJSON);
        User user = new User();
        user.setUserOpenid(userInfoJSON.get("openId").toString());
        user.setUserSessionKey(sessionKey);
        user.setUserAvatarUrl(userInfoJSON.get("avatarUrl").toString());
        user.setUserNickname(userInfoJSON.get("nickName").toString());
        user.setUserCity(userInfoJSON.get("city").toString());
        return user;
    }


}
