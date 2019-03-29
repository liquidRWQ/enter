package com.enter.repair2.DTO;

import com.enter.repair2.entity.MiniprogramNotice;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Liquid
 * @类名： EnterpriseWeChatTemplateDTO
 * @描述：
 * @date 2019/1/8
 */
@Data
public class EnterpriseWeChatTemplateDTO implements Serializable{
    private static final long serialVersionUID = -4774301254866236848L;

    String touser;

    String toparty;

    String totag;

    String msgtype;

    MiniprogramNotice miniprogramNotice;

}
