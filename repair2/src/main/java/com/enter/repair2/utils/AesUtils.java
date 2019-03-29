package com.enter.repair2.utils;

import com.enter.repair2.exception.CheckedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.UUID;

/**
 * @author Liquid
 * @className AesUtil
 * @description
 * @date 2018/12/26
 */
public class AesUtils {
    /**
    *   默认加密类型
    */
    private static final String DEFAULT_AES_TYPE = "AES/CBC/PKCS5Padding";
    /**
     *   默认加密类型
     */
    private static final String WE_CHAT_AES_TYPE = "AES/CBC/PKCS7Padding";
    /**
     *   默认加密类型
     */
    private static final String NO_AES_TYPE = "AES/CBC/NoPadding";
    /**
     *   加密方式
     */
    private static final String ENCRYPTION = "AES";
    /**
     *   解码类型
     */
    private static final String ENCODING_FORMAT = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * @param data,key,iv
     * @return 解密后的数据
     * @auther Liquid
     * @description 对微信的AES解密，PKCS7Padding
     * @date 2018/12/26
     */
    public static String decrypt(String data, String key, String iv) throws CheckedException {

        byte[] dataByte = Base64Utils.base64StringDecodeToBytes(data);
        byte[] keyByte = Base64Utils.base64StringDecodeToBytes(key);
        byte[] ivByte = Base64Utils.base64StringDecodeToBytes(iv);

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, ENCRYPTION);
            Cipher cipher = Cipher.getInstance(WE_CHAT_AES_TYPE);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(ENCRYPTION);
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            String result = new String(resultByte, ENCODING_FORMAT);
            return result;
        } catch (Exception e) {
            throw new CheckedException("微信AES解密异常:" + e.toString());
        }

    }

    /**
     * @param data,key,iv
     * @return 加密后的数据的base64编码字符串
     * @auther Liquid
     * @description 默认AES加密，PKCS5Padding
     * @date 2018/12/23
     */
    public static String aseDefaultEncrypt(String data, String key, String iv) throws CheckedException {
        byte[] dataBytes = data.getBytes();
        byte[] keyBytes = Base64Utils.base64StringDecodeToBytes(key);
        byte[] ivBytes = Base64Utils.base64StringDecodeToBytes(iv);

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ENCRYPTION);
            Cipher cipher = Cipher.getInstance(DEFAULT_AES_TYPE);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(ENCRYPTION);
            parameters.init(ivParameterSpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            byte[] resultByte = cipher.doFinal(dataBytes);
            String result = Base64Utils.bytesEncodeToBase64String(resultByte);
            return result;
        } catch (Exception e) {
            throw new CheckedException("AES加密异常:" + e.toString());
        }
    }

    /**
     * @param dataBytes,keyBytes,ivBytes
     * @return 加密后的数据
     * @author Liquid
     * @description 对企业微信的AES加密
     * @date 2018/12/26
     */
    public static byte[] enterpriseWeChatMessageEncrypt(byte[] dataBytes, byte[] keyBytes, byte[] ivBytes) throws CheckedException {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ENCRYPTION);
            Cipher cipher = Cipher.getInstance(NO_AES_TYPE);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(ENCRYPTION);
            parameters.init(ivParameterSpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            byte[] resultBytes = cipher.doFinal(dataBytes);
            return resultBytes;
        } catch (Exception e) {
            throw new CheckedException("AES加密异常:" + e.toString());
        }
    }

    /**
     * @param data,key,iv
     * @return 解密后的数据字符串
     * @author Liquid
     * @description 默认AES解密，PKCS5Padding
     * @date 2018/12/23
     */
    public static String aseDefaultDecrypt(String data, String key, String iv) throws CheckedException {

        byte[] dataBytes = Base64Utils.base64StringDecodeToBytes(data);
        byte[] keyBytes = Base64Utils.base64StringDecodeToBytes(key);
        byte[] ivBytes = Base64Utils.base64StringDecodeToBytes(iv);
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ENCRYPTION);
            Cipher cipher = Cipher.getInstance(DEFAULT_AES_TYPE);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(ENCRYPTION);
            parameters.init(ivParameterSpec);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            byte[] resultByte = cipher.doFinal(dataBytes);
            String result = new String(resultByte, ENCODING_FORMAT);
            return result;
        } catch (Exception e) {
            throw new CheckedException("AES解密异常:" + e.toString());
        }

    }

    /**
     * @param dataByte,keyByte,ivByte
     * @return
     * @author Liquid
     * @description
     * @date 2018/12/26
     */
    public static byte[] decryptBytes(byte[] dataByte, byte[] keyByte, byte[] ivByte) throws CheckedException {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, ENCRYPTION);
            Cipher cipher = Cipher.getInstance(NO_AES_TYPE);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(ENCRYPTION);
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            byte[] resultBytes = cipher.doFinal(dataByte);
            return resultBytes;
        } catch (Exception e) {
            throw new CheckedException("企业微信AES解密异常:" + e.toString());
        }

    }

    /**
     * @param
     * @return Base64秘钥字符串
     * @author Liquid
     * @description 获取随机Aes秘钥Base64字符串
     * @date 2018/12/26
     */
    public static String getBase64AesKey() throws CheckedException {

        byte[] aesKey = AesKeyGeneratorUtils.getAesKey();
        String base64AesKey = Base64Utils.bytesEncodeToBase64String(aesKey);
        return base64AesKey;
    }

    /**
     * @param seed
     * @return Base64秘钥字符串
     * @auther Liquid
     * @description 获取唯一Aes秘钥Base64字符串
     * @date 2018/12/23
     */
    public static String getBase64AesKeyBySeed(String seed) throws CheckedException {

        byte[] aesKey = AesKeyGeneratorUtils.getKeyBySeed(seed);
        String base64AesKey = Base64Utils.bytesEncodeToBase64String(aesKey);
        return base64AesKey;
    }

    /**
     * @param
     * @return Base64iv字符串
     * @auther Liquid
     * @description 获取随机iv
     * @date 2018/12/23
     */
    public static String getBase64Iv() {

        String uuid = UUID.randomUUID().toString().substring(0, 16).replace("-", "0");
        String base64Iv = Base64Utils.stringEncodeToBase64String(uuid);
        return base64Iv;
    }

}
