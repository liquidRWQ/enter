package com.enter.repair2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liquid
 * @类名： WeChatTemplateData
 * @描述：
 * @date 2019/1/8
 */
@Data
public class WeChatTemplateData implements Serializable {

    private static final long serialVersionUID = -305584893525352841L;

    String key;

    String value;
}
