package com.enter.repair2.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @className WeChatLoginDTO
 * @auther Liquid
 * @description
 * @date 2018/12/22
 */
@Data
public class WeChatLoginDTO {

    /**
     * 用户私密数据
     */
    @NotBlank
    String encryptedData;

    /**
     * 秘钥向量
     */
    @NotBlank
    String iv;

    /**
     * 微信小程序的用户code
     */
    @NotBlank
    String code;
}
