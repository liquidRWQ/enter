package com.enter.repair2.controller;

import com.enter.repair2.DTO.EnterpriseWeChatMessageDTO;
import com.enter.repair2.DTO.ManagerDTO;
import com.enter.repair2.service.ManagerService;
import com.enter.repair2.utils.EnterpriseWeChatUtil.EnterpriseWeChatMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liquid
 * @类名： WeChatPublicController
 * @描述：
 * @date 2018/12/30
 */
@Slf4j
@RestController
public class WeChatPublicController {

    private static final String MSG_ID = "664";

    private static final String MSG_TYPE = "MsgType";

    @Autowired
    private ManagerService managerService;

    public String verifyURL(String signature, EnterpriseWeChatMessageDTO enterpriseWeChatMessageDTO) throws Exception {
        log.info("signature = " + signature);
        log.info("enterpriseWeChatMessageDTO = " + enterpriseWeChatMessageDTO);
        String xml;
        xml = EnterpriseWeChatMessageUtil.verifyURL(signature, enterpriseWeChatMessageDTO);
        return xml;
    }

    @PostMapping("/verifyURL")
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        log.info("xml = " + map);
        inputStream.close();

        if (map.get(MSG_TYPE) != null) {
            if ("text".equals(map.get(MSG_TYPE)) && map.get("Content").startsWith("管理员设置")) {
                ManagerDTO managerDTO = new ManagerDTO();
                managerDTO.setPublicOpenId(map.get("FromUserName"));
                managerService.insertOneManagerPublicId(managerDTO);
            }
        }

        return map;
    }

}
