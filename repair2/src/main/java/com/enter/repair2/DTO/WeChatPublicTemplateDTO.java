package com.enter.repair2.DTO;

import com.enter.repair2.entity.Miniprogram;
import com.enter.repair2.entity.TemplateData;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Liquid
 * @类名： WeChatPublicTemplateDTO
 * @描述：
 * @date 2018/12/29
 */
@Data
public class WeChatPublicTemplateDTO implements Serializable {

    private static final long serialVersionUID = -4761602383033259593L;

    private String templateId;
    private String touser;
    private String url;
    private Miniprogram miniprogram;
    private Map<String, TemplateData> data;
    private String topcolor;

}
