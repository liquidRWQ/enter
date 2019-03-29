package com.enter.repair2.utils.TemplateMessageUtils;

import com.alibaba.fastjson.JSONObject;
import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.HttpRequestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @className WeChatMessageUtil
 * @auther Liquid
 * @description
 * @date 2018/12/21
 */
@Slf4j
public class WeAppMessageUtil {


    public static void sendMessageToUser(OrderDTO orderDTO) throws CheckedException {
        String[] formIds = orderDTO.getFormIds();
        String formId = formIds[0];
        String touser = orderDTO.getUserOpenId();
        String params = WeChatMessageParamsUtils.getOrderDTOJsonParams(orderDTO, formId, touser);
        String json = HttpRequestUtils.sendPost(WeChatTemplateMessageConfig.WE_APP_URL + RepairAppConfig.accessToken, params);
        String errcode = JSONObject.parseObject(json).get("errcode").toString();
        if (!WeChatTemplateMessageConfig.CORRECT_CODE.equals(errcode)) {
            String errmsg = JSONObject.parseObject(json).get("errmsg").toString();
            throw new CheckedException("发送给用户订单模板通知失败：errmsg: " + errmsg);
        }
    }

}
