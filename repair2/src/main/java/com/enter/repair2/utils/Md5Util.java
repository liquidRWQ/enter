package com.enter.repair2.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 类名： Md5Util
 *
 * @author Liquid
 * <p>
 * 描述：MD5加密工具类
 * @date 2018/12/26
 */
public class Md5Util {
    /**
     * 登录密码加密的次数
     */
    public static final Integer LOGIN_HASH_ITERATIONS = 1024;
    /**
     * 数据签名的加密次数
     */
    public static final Integer SIGNATURE_HASH_ITERATIONS = 2;

    /**
     * @param params 描述：md5普通加密
     * @return java.lang.String 加密后的散列码
     * @author Liquid
     * @date 2018/12/27
     */
    public static String md5SignatureEncryptWithoutSalt(String params) {

        SimpleHash md5 = new SimpleHash("MD5", params);
        String result = md5.toString();
        return result;
    }

    public static String md5SignatureEncrypt(String params, String salt) {
        /**
         * @author Liquid
         *
         * @param params
         * @param salt
         *
         * 描述：md5盐值加密(签名加密)
         *
         * @return java.lang.String 加密后的散列码
         *
         * @date 2018/12/27 
         */
        Object md5Salt = ByteSource.Util.bytes(salt);
        SimpleHash md5 = new SimpleHash("MD5", params, md5Salt, SIGNATURE_HASH_ITERATIONS);
        String result = md5.toString();
        return result;
    }

    /**
     * @param password
     * @param salt     描述：md5盐值加密（登录密码加密）
     * @return java.lang.String
     * @author Liquid
     * @date 2018/12/27
     */
    public static String md5LoginEncrypt(String password, String salt) {

        Object md5Salt = ByteSource.Util.bytes(salt);
        SimpleHash md5 = new SimpleHash("MD5", password, md5Salt, LOGIN_HASH_ITERATIONS);
        String result = md5.toString();
        return result;
    }

}
