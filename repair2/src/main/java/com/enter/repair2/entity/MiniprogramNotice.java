package com.enter.repair2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liquid
 * @类名： MiniprogramNotice
 * @描述：
 * @date 2019/1/8
 */
@Data
public class MiniprogramNotice implements Serializable {

    private static final long serialVersionUID = 346254236173740622L;

    String appid;

    String page;

    String title;

    String description;

    Boolean emphasisFirstItem;

    List<WeChatTemplateData> contentItem;
}
