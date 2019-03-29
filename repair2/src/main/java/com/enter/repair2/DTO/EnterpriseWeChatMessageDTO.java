package com.enter.repair2.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @className EnterpriseWeChatMessageDTO
 * @auther Liquid
 * @description
 * @date 2018/12/24
 */
@Data
public class EnterpriseWeChatMessageDTO implements Serializable{

    private static final long serialVersionUID = -3222586309658051486L;
    /**
     *  令牌签名
     */
    String token;

    /**
     * 时间戳，签名内也存在
     */
    String timestamp;

    /**
     * 随机数，签名内也存在
     */
    String nonce;

    /**
     * 加密的数据
     */
    String encrypt;


    /**
     * 数据密文
     */
    String postData;

    /**
    *   返回的信息
    */
    String replyMsg;

    String echostr;


    String xml;

}
