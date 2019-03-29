package com.enter.repair2.utils.TemplateMessageUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.enter.repair2.DTO.*;
import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.entity.Miniprogram;
import com.enter.repair2.entity.MiniprogramNotice;
import com.enter.repair2.entity.TemplateData;
import com.enter.repair2.entity.WeChatTemplateData;
import com.enter.repair2.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Liquid
 * @类名： WeChatMessageParamsUtils
 * @描述：
 * @date 2019/1/8
 */
public class WeChatMessageParamsUtils {

    private static SerializeConfig serializeConfig;

    static {
        serializeConfig = new SerializeConfig();
        serializeConfig.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
    }

    static String getPublicOrderDTOJsonParams(OrderDTO orderDTO, String openId) {
        Map<String, TemplateData> data = new HashMap<>();
        TemplateData first = new TemplateData();
        TemplateData keyword1 = new TemplateData();
        TemplateData keyword2 = new TemplateData();
        TemplateData keyword3 = new TemplateData();
        TemplateData keyword4 = new TemplateData();
        TemplateData remark = new TemplateData();
        first.setValue("维修订单提醒");
        first.setColor("#173177");
        keyword1.setValue("上门");
        keyword1.setColor("#173177");
        keyword2.setValue(orderDTO.getDeviceType());
        keyword2.setColor("#173177");
        keyword3.setValue(TimeUtils.dateDefaultFormatToString(orderDTO.getRepairTime()));
        keyword3.setColor("#173177");
        keyword4.setValue(orderDTO.getDetail());
        keyword4.setColor("#173177");
        remark.setValue("姓名：" + orderDTO.getName() + "  手机：" + orderDTO.getPhone() + "  价格：" + orderDTO.getPrice());
        remark.setColor("#173177");
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("remark", remark);
        Miniprogram miniprogram = new Miniprogram();
        miniprogram.setAppid(RepairAppConfig.APP_ID);
        miniprogram.setPagepath(WeChatTemplateMessageConfig.MANAGER_ORDER_PAGE);
        WeChatPublicTemplateDTO weChatPublicTemplateDTO = new WeChatPublicTemplateDTO();
        weChatPublicTemplateDTO.setData(data);
        weChatPublicTemplateDTO.setTemplateId(WeChatTemplateMessageConfig.REPAIRMAN_JOB_MESSAGE_ID);
        weChatPublicTemplateDTO.setTouser(openId);
        weChatPublicTemplateDTO.setMiniprogram(miniprogram);
        return JSONObject.toJSONString(weChatPublicTemplateDTO, serializeConfig, SerializerFeature.DisableCircularReferenceDetect);

    }

    static String getFeedbackDTOJsonParams(FeedbackDTO feedbackDTO, String touser) {

        Map<String, TemplateData> data = new HashMap<>();
        TemplateData first = new TemplateData();
        TemplateData keyword2 = new TemplateData();
        TemplateData keyword4 = new TemplateData();
        TemplateData remark = new TemplateData();
        first.setValue("收到用户反馈信息");
        first.setColor("#173177");
        keyword2.setValue(feedbackDTO.getAdvice());
        keyword2.setColor("#173177");
        keyword4.setValue(TimeUtils.getCurrentTimeString());
        keyword4.setColor("#173177");
        String phone = StringUtils.isBlank(feedbackDTO.getTelephone()) ? "无" : feedbackDTO.getTelephone();
        remark.setValue("手机：" + phone);
        remark.setColor("#173177");
        data.put("first", first);
        data.put("keyword2", keyword2);
        data.put("keyword4", keyword4);
        data.put("remark", remark);
        Miniprogram miniprogram = new Miniprogram();
        miniprogram.setAppid(RepairAppConfig.APP_ID);
        miniprogram.setPagepath(WeChatTemplateMessageConfig.MANAGER_ORDER_PAGE);
        WeChatPublicTemplateDTO weChatPublicTemplateDTO = new WeChatPublicTemplateDTO();
        weChatPublicTemplateDTO.setData(data);
        weChatPublicTemplateDTO.setTemplateId(WeChatTemplateMessageConfig.FEEDBACK_MESSAGE_ID);
        weChatPublicTemplateDTO.setTouser(touser);
        weChatPublicTemplateDTO.setMiniprogram(miniprogram);
        return JSONObject.toJSONString(weChatPublicTemplateDTO, serializeConfig, SerializerFeature.DisableCircularReferenceDetect);

    }

    static String getOrderDTOJsonParams(OrderDTO orderDTO, String to) {
        WeChatTemplateData deviceType = new WeChatTemplateData();
        WeChatTemplateData faultType = new WeChatTemplateData();
        WeChatTemplateData detail = new WeChatTemplateData();
        WeChatTemplateData name = new WeChatTemplateData();
        WeChatTemplateData phone = new WeChatTemplateData();
        WeChatTemplateData detailAddress = new WeChatTemplateData();
        WeChatTemplateData price = new WeChatTemplateData();
        WeChatTemplateData repairWay = new WeChatTemplateData();
        WeChatTemplateData repairTime = new WeChatTemplateData();
        deviceType.setKey("维修设备");
        deviceType.setValue(orderDTO.getDeviceType());
        faultType.setKey("故障类型");
        faultType.setValue(orderDTO.getFaultType());
        detail.setKey(StringUtils.isBlank(orderDTO.getFaultType()) ? "无" : orderDTO.getFaultType());
        detail.setValue(StringUtils.isBlank(orderDTO.getDetail()) ? "无" : orderDTO.getDetail());
        name.setKey("报修人");
        name.setValue(orderDTO.getName());
        phone.setKey("报修人联系电话");
        phone.setValue(orderDTO.getPhone());
        detailAddress.setKey("报修人地址");
        detailAddress.setValue(orderDTO.getDetailAddress());
        price.setKey("价格");
        price.setValue(orderDTO.getPrice().toString());
        repairWay.setKey("维修方式");
        repairWay.setValue("上门");
        repairTime.setKey("上门维修时间");
        repairTime.setValue(TimeUtils.dateDefaultFormatToString(orderDTO.getRepairTime()));
        List<WeChatTemplateData> contentItem = new LinkedList<>();
        contentItem.add(deviceType);
        contentItem.add(faultType);
        contentItem.add(detail);
        contentItem.add(name);
        contentItem.add(phone);
        contentItem.add(detailAddress);
        contentItem.add(price);
        contentItem.add(repairWay);
        contentItem.add(repairTime);
        MiniprogramNotice miniprogramNotice = new MiniprogramNotice();
        miniprogramNotice.setAppid(RepairAppConfig.APP_ID);
        miniprogramNotice.setPage(WeChatTemplateMessageConfig.ORDER_PAGE);
        miniprogramNotice.setTitle("维修订单通知");
        miniprogramNotice.setDescription(TimeUtils.getCurrentTimeString());
        miniprogramNotice.setEmphasisFirstItem(true);
        miniprogramNotice.setContentItem(contentItem);
        EnterpriseWeChatTemplateDTO enterpriseWeChatTemplateDTO = new EnterpriseWeChatTemplateDTO();
        enterpriseWeChatTemplateDTO.setToparty(to);
        enterpriseWeChatTemplateDTO.setMsgtype(WeChatTemplateMessageConfig.MSGTYPE);
        enterpriseWeChatTemplateDTO.setMiniprogramNotice(miniprogramNotice);
        return JSONObject.toJSONString(enterpriseWeChatTemplateDTO, serializeConfig, SerializerFeature.DisableCircularReferenceDetect);
    }

    static String getFeedbackDTOJsonParamsToEnterpriseWeChat(FeedbackDTO feedbackDTO, String to) {
        WeChatTemplateData phone = new WeChatTemplateData();
        WeChatTemplateData advice = new WeChatTemplateData();
        phone.setKey("反馈人联系电话");
        phone.setValue(StringUtils.isBlank(feedbackDTO.getTelephone()) ? "无" : feedbackDTO.getTelephone());
        advice.setKey("反馈内容");
        advice.setValue(feedbackDTO.getAdvice());
        List<WeChatTemplateData> contentItem = new LinkedList<>();
        contentItem.add(phone);
        contentItem.add(advice);
        MiniprogramNotice miniprogramNotice = new MiniprogramNotice();
        miniprogramNotice.setAppid(RepairAppConfig.APP_ID);
        miniprogramNotice.setPage(WeChatTemplateMessageConfig.ORDER_PAGE);
        miniprogramNotice.setTitle("用户反馈通知");
        miniprogramNotice.setDescription(TimeUtils.getCurrentTimeString());
        miniprogramNotice.setEmphasisFirstItem(false);
        miniprogramNotice.setContentItem(contentItem);
        EnterpriseWeChatTemplateDTO enterpriseWeChatTemplateDTO = new EnterpriseWeChatTemplateDTO();
        enterpriseWeChatTemplateDTO.setToparty(to);
        enterpriseWeChatTemplateDTO.setMsgtype(WeChatTemplateMessageConfig.MSGTYPE);
        enterpriseWeChatTemplateDTO.setMiniprogramNotice(miniprogramNotice);
        return JSONObject.toJSONString(enterpriseWeChatTemplateDTO, serializeConfig, SerializerFeature.DisableCircularReferenceDetect);
    }

    static String getOrderDTOJsonParams(OrderDTO orderDTO, String formId, String touser) {
        String orderRepairTime = TimeUtils.dateDefaultFormatToString(orderDTO.getRepairTime());
        HashMap<String, String> name = new HashMap<>(2);
        HashMap<String, String> phone = new HashMap<>(2);
        HashMap<String, String> detailAddress = new HashMap<>(2);
        HashMap<String, String> faultType = new HashMap<>(2);
        HashMap<String, String> detail = new HashMap<>(2);
        HashMap<String, String> userRemark = new HashMap<>(2);
        HashMap<String, String> repairTime = new HashMap<>(2);
        HashMap<String, String> deviceType = new HashMap<>(2);
        HashMap<String, HashMap<String, String>> data = new HashMap<>(10);
        name.put("value", orderDTO.getName());
        phone.put("value", orderDTO.getPhone());
        detailAddress.put("value", orderDTO.getDetailAddress());
        faultType.put("value", orderDTO.getFaultType());
        detail.put("value", orderDTO.getDetail());
        repairTime.put("value", orderRepairTime);
        userRemark.put("value", orderDTO.getUserRemark());
        deviceType.put("value", orderDTO.getDeviceType());
        data.put("keyword1", name);
        data.put("keyword2", phone);
        data.put("keyword3", detailAddress);
        data.put("keyword4", faultType);
        data.put("keyword5", detail);
        data.put("keyword6", repairTime);
        data.put("keyword7", userRemark);
        data.put("keyword8", deviceType);
        WeChatTemplateDTO weChatTemplateDTO = new WeChatTemplateDTO();
        weChatTemplateDTO.setTouser(touser);
        weChatTemplateDTO.setTemplateId(WeChatTemplateMessageConfig.USER_MESSAGE_ID);
        weChatTemplateDTO.setPage(WeChatTemplateMessageConfig.ORDER_PAGE);
        weChatTemplateDTO.setFormId(formId);
        weChatTemplateDTO.setData(data);
        weChatTemplateDTO.setEmphasisKeyword(WeChatTemplateMessageConfig.EMPHASIS_KEYWORD);
        return JSONObject.toJSONString(weChatTemplateDTO, serializeConfig, SerializerFeature.DisableCircularReferenceDetect);
    }

}
