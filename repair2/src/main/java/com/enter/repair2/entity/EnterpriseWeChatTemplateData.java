package com.enter.repair2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liquid
 * @类名： EnterpriseWeChatTemplateData
 * @描述：
 * @date 2019/1/8
 */
@Data
public class EnterpriseWeChatTemplateData implements Serializable {
    private static final long serialVersionUID = 2281755622155507671L;

    String appid;

    String page;

    String title;

    String description;

    Boolean emphasisFirstItem;

    List<WeChatTemplateData> contentItem;

}
