package com.enter.repair2.utils.EnterpriseWeChatUtil;

import com.enter.repair2.DTO.EnterpriseWeChatMessageDTO;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.utils.AesUtils;
import com.enter.repair2.utils.Base64Utils;
import com.enter.repair2.utils.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import static com.enter.repair2.utils.SignatureUtils.getSha1Signature;

/**
 * @className EnterpriseWeChatMessageUtil
 * @auther Liquid
 * @description
 * @date 2018/12/24
 */
@Slf4j
public class EnterpriseWeChatMessageUtil {
    private static final String TOKEN = "huizekeji";
    private static Charset CHARSET = Charset.forName("utf-8");
    private static final byte[] AES_KEY = Base64.decodeBase64("hAMvyRnsAFwaFlKwfZhqcTBMdcAgBuS2vKpdOyPO2ds=");
    private static final byte[] REPLAY_AES_KEY = Base64.decodeBase64("jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C=");
    private static final String RECEIVE_ID = "wx5823bf96d3bd56c7";

    /**
     * 验证URL
     *
     * @return 解密之后的echostr
     * @throws CheckedException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String verifyURL(String msgSignature, EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO) throws CheckedException {
        enterpriseWeChatMessageDTO.setToken(TOKEN);
        String signature = getSha1Signature(enterpriseWeChatMessageDTO);
        log.info("msg_signature:" + msgSignature);
        log.info("signature:" + signature);
        if (!signature.equals(msgSignature)) {
            throw new UnCheckedException("验证签名失败");
        }

        return enterpriseWeChatMessageDTO.getEchostr();
    }

    /**
     * 对密文进行解密.
     *
     * @param text 需要解密的密文
     * @return 解密得到的明文
     * @throws CheckedException aes解密失败
     */
    static String decrypt(String text) throws CheckedException {
        byte[] ivBtyes = Arrays.copyOfRange(AES_KEY, 0, 16);
        byte[] encrypted = Base64Utils.base64StringDecodeToBytes(text);
        byte[] original = AesUtils.decryptBytes(encrypted, AES_KEY, ivBtyes);
        String xmlContent, from_receiveid;
        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);
            System.out.println("bytes = " + new String(bytes));
            // 分离16位随机字符串,网络字节序和receiveid
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
            int xmlLength = WXBizMsgCrypt.recoverNetworkBytesOrder(networkOrder);
            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            from_receiveid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                    CHARSET);
        } catch (Exception e) {
            throw new CheckedException("AES解密失败" + e.toString());
        }

        return xmlContent;

    }

    /**
     * 检验消息的真实性，并且获取解密后的明文.
     * <ol>
     * <li>利用收到的密文生成安全签名，进行签名验证</li>
     * <li>若验证通过，则提取xml中的加密消息</li>
     * <li>对消息进行解密</li>
     * </ol>
     *
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @return 解密后的原文
     * @throws CheckedException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static String getEnterpriseWeChatMessage(String msgSignature, EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO) throws CheckedException {

        // 密钥，公众账号的app secret
        // 提取密文
        Object[] encrypt = XMLParse.extract(enterpriseWeChatMessageDTO.getPostData());
        enterpriseWeChatMessageDTO.setEncrypt(encrypt[1].toString());
        System.out.println("encrypt = " + encrypt[1].toString());
        enterpriseWeChatMessageDTO.setToken(TOKEN);
        // 验证安全签名
        String signature = SignatureUtils.getSha1Signature(enterpriseWeChatMessageDTO);

        if (!signature.equals(msgSignature)) {
            throw new UnCheckedException("验证签名失败");
        }
        // 解密
        String decrypted = decrypt(encrypt[1].toString());

        String replyMsg = "";
        String timestamp = "";
        String nouce = "";
        EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO1 = new EnterpriseWeChatMessageDTO();
        enterpriseWeChatMessageDTO1.setToken(TOKEN);
        String xml = replyMsg(enterpriseWeChatMessageDTO1);
        return xml;
    }

    /**
     * 将企业微信回复用户的消息加密打包.
     * <ol>
     * <li>对要发送的消息进行AES-CBC加密</li>
     * <li>生成安全签名</li>
     * <li>将消息密文和安全签名打包成xml格式</li>
     * </ol>
     *
     * @param enterpriseWeChatMessageDTO 企业微信待回复用户的消息，xml格式的字符串
     * @param enterpriseWeChatMessageDTO 时间戳，可以自己生成，也可以用URL参数的timestamp
     * @param enterpriseWeChatMessageDTO 随机串，可以自己生成，也可以用URL参数的nonce
     * @return 加密后的可以直接回复用户的密文，包括msg_signature, timestamp, nonce, encrypt的xml格式的字符串
     * @throws CheckedException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    private static String replyMsg(EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO) throws CheckedException {
        // 加密
        String encrypt = encrypt(getRandomStr(), enterpriseWeChatMessageDTO.getReplyMsg());

        // 生成安全签名
        if (enterpriseWeChatMessageDTO.getTimestamp() == "") {
            enterpriseWeChatMessageDTO.setTimestamp(Long.toString(System.currentTimeMillis()));
        }
        enterpriseWeChatMessageDTO.setEncrypt(encrypt);
        String signature = SignatureUtils.getSha1Signature(enterpriseWeChatMessageDTO);

        // 生成发送的xml
        String result = XMLParse.generate(encrypt, signature, enterpriseWeChatMessageDTO.getTimestamp(), enterpriseWeChatMessageDTO.getNonce());
        return result;
    }

    /**
     * 对明文进行加密.
     *
     * @param text 需要加密的明文
     * @return 加密后base64编码的字符串
     * @throws CheckedException aes加密失败
     */
    private static String encrypt(String randomStr, String text) throws CheckedException {
        ByteGroup byteCollector = new ByteGroup();
        byte[] randomStrBytes = randomStr.getBytes(CHARSET);
        byte[] textBytes = text.getBytes(CHARSET);
        byte[] networkBytesOrder = new WXBizMsgCrypt().getNetworkBytesOrder(textBytes.length);
        byte[] receiveidBytes = RECEIVE_ID.getBytes(CHARSET);
        // randomStr + networkBytesOrder + text + receiveid
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(textBytes);
        byteCollector.addBytes(receiveidBytes);

        // ... + pad: 使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);

        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.toBytes();
        byte[] ivBytes = Arrays.copyOfRange(REPLAY_AES_KEY, 0, 16);
        byte[] encrypted = AesUtils.enterpriseWeChatMessageEncrypt(unencrypted, REPLAY_AES_KEY, ivBytes);
        String base64Encrypted = Base64Utils.bytesEncodeToBase64String(encrypted);
        return base64Encrypted;
    }

    // 随机生成16位字符串
    private static String getRandomStr() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
