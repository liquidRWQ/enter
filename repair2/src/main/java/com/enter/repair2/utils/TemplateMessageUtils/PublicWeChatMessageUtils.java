package com.enter.repair2.utils.TemplateMessageUtils;

import com.alibaba.fastjson.JSONObject;
import com.enter.repair2.DTO.FeedbackDTO;
import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.HttpRequestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Liquid
 * @类名： PublicWeChatMessageUtils
 * @描述：
 * @date 2019/1/8
 */
@Slf4j
public class PublicWeChatMessageUtils {

    public static void sendFeedbackMessageToManager(FeedbackDTO feedbackDTO, String publicOpenId) throws CheckedException {
        String params = WeChatMessageParamsUtils.getFeedbackDTOJsonParams(feedbackDTO, publicOpenId);
        String json = HttpRequestUtils.sendPost(WeChatTemplateMessageConfig.WE_PUBLIC_URL + RepairAppConfig.publicAccessToken, params);
        String errcode = JSONObject.parseObject(json).get("errcode").toString();
        if (!WeChatTemplateMessageConfig.CORRECT_CODE.equals(errcode)) {
            String errmsg = JSONObject.parseObject(json).get("errmsg").toString();
            if (errmsg.contains("latest hint")) {
                log.warn("token失效{}", RepairAppConfig.publicAccessToken);
            }

            throw new CheckedException("发送给维修人员反馈模板通知失败：errmsg: " + errmsg);
        }
    }

    public static void sendOrderMessageToRepairman(OrderDTO orderDTO, String publicOpenId) throws CheckedException {
        String params = WeChatMessageParamsUtils.getPublicOrderDTOJsonParams(orderDTO, publicOpenId);
        String json = HttpRequestUtils.sendPost(WeChatTemplateMessageConfig.WE_PUBLIC_URL + RepairAppConfig.publicAccessToken, params);
        String errcode = JSONObject.parseObject(json).get("errcode").toString();
        if (!WeChatTemplateMessageConfig.CORRECT_CODE.equals(errcode)) {
            String errmsg = JSONObject.parseObject(json).get("errmsg").toString();
            if (errmsg.contains("latest hint")) {
                log.warn("token失效{}", RepairAppConfig.publicAccessToken);
            }
            throw new CheckedException("发送给维修人员订单模板通知失败：errmsg: " + errmsg + "errcode" + errcode);
        }
    }

}
