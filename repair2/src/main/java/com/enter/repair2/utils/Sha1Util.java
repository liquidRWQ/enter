package com.enter.repair2.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @className SHA1Util
 * @auther Liquid
 * @description
 * @date 2018/12/24
 */
public class Sha1Util {
    public static String sha1SignatureEncrypt(String params){
        SimpleHash sha1 = new SimpleHash("SHA1", params);
        String result = sha1.toString();
        return result;
    }
}
