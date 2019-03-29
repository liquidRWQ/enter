package com.enter.repair2.utils;

import com.enter.repair2.DTO.EnterpriseWeChatMessageDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @className SignatureUtil
 * @auther Liquid
 * @description 签名工具类
 * @date 2018/12/23
 */
@Slf4j
public class SignatureUtils {

    private static final int PARAMS_LENGTH = 3;

    public static String getSha1Signature(EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO) {
        String token = enterpriseWeChatMessageDTO.getToken();
        String timestamp = enterpriseWeChatMessageDTO.getTimestamp();
        String nonce = enterpriseWeChatMessageDTO.getNonce();
        String encrypt = enterpriseWeChatMessageDTO.getEchostr();
        String[] array = new String[]{token, timestamp, nonce};
        StringBuffer stringBuffer = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < PARAMS_LENGTH; i++) {
            stringBuffer.append(array[i]);
        }
        String str = stringBuffer.toString();
        // SHA1签名生成
        String sha1Str = Sha1Util.sha1SignatureEncrypt(str);
        return sha1Str;
    }

    public static String signatureData(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg);
        }
        String str = stringBuilder.toString();
        String result = Md5Util.md5SignatureEncryptWithoutSalt(str);
        return result;
    }

    public static boolean checkSignature(String signature, String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg);
        }
        String str = stringBuilder.toString();
        String result = Md5Util.md5SignatureEncryptWithoutSalt(str);
        if (result.equals(signature)) {
            return true;
        } else {
            return false;
        }
    }

}
