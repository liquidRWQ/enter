package com.enter.repair2.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @className WeChatMessageDTO
 * @auther Liquid
 * @description
 * @date 2018/12/21
 */
@Data
public class WeChatTemplateDTO implements Serializable{
    private static final long serialVersionUID = -7391194400703163319L;
    String touser;

    String toparty;

    String totag;

    String msgtype;


    String templateId;

    String page;

    String formId;

    HashMap<String, HashMap<String, String>> data;

    String emphasisKeyword;

}
