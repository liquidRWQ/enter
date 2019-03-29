package com.enter.repair2.utils.TemplateMessageUtils;

import com.alibaba.fastjson.JSONObject;
import com.enter.repair2.DTO.FeedbackDTO;
import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.HttpRequestUtils;

/**
 * @author Liquid
 * @类名： EnterpriseWeChatMessageUtils
 * @描述：
 * @date 2019/1/8
 */
public class EnterpriseWeChatMessageUtils {


    public static void sendOrderToCorporateSector(OrderDTO orderDTO) throws CheckedException {
        String params =WeChatMessageParamsUtils.getOrderDTOJsonParams(orderDTO,WeChatTemplateMessageConfig.TO_PARTY_ID);
        String json = HttpRequestUtils.sendPost(WeChatTemplateMessageConfig.ENTERPRISE_WE_CHAT_URL+ RepairAppConfig.enterpriseWeChatAccessToken, params);
        String errcode = JSONObject.parseObject(json).get("errcode").toString();
        if (!WeChatTemplateMessageConfig.CORRECT_CODE.equals(errcode)) {
            String errmsg = JSONObject.parseObject(json).get("errmsg").toString();
            throw new CheckedException("发送给企业订单通知失败：errmsg: " + errmsg);
        }
    }

    public static void sendFeedbackToCorporateSector(FeedbackDTO feedbackDTO) throws CheckedException {
        String params =WeChatMessageParamsUtils.getFeedbackDTOJsonParamsToEnterpriseWeChat(feedbackDTO,WeChatTemplateMessageConfig.TO_PARTY_ID);
        String json = HttpRequestUtils.sendPost(WeChatTemplateMessageConfig.ENTERPRISE_WE_CHAT_URL+ RepairAppConfig.enterpriseWeChatAccessToken, params);
        String errcode = JSONObject.parseObject(json).get("errcode").toString();
        if (!WeChatTemplateMessageConfig.CORRECT_CODE.equals(errcode)) {
            String errmsg = JSONObject.parseObject(json).get("errmsg").toString();
            throw new CheckedException("发送给企业反馈通知失败：errmsg: " + errmsg);
        }
    }

}
