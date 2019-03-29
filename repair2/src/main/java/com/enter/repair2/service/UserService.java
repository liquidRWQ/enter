package com.enter.repair2.service;

import com.enter.repair2.DTO.WeChatLoginDTO;
import com.enter.repair2.entity.User;

/**
 * @className UserService
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
public interface UserService {

    String simpleLogin(WeChatLoginDTO weChatLoginDTO) throws Exception;

    String getUserOpenId(User user);

}
