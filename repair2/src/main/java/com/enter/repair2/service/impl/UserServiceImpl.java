package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.WeChatLoginDTO;
import com.enter.repair2.entity.User;
import com.enter.repair2.mapper.UserMapper;
import com.enter.repair2.service.UserService;
import com.enter.repair2.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className UserServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String simpleLogin(WeChatLoginDTO weChatLoginDTO) throws Exception {

        User user = CodeUtil.getUserOpenIdByWeChatLoginDTO(weChatLoginDTO);
        String userId = userMapper.selectUserId(user.getUserOpenid());
        if (userId != null) {
            user.refreshLastUpdateTime();
            userMapper.updateByOpenId(user);
            return userId;
        } else {
            user.setUserIdAndAllTime();
            userMapper.insert(user);
            return user.getUserId();
        }

    }

    @Override
    public String getUserOpenId(User user) {
        return userMapper.selectByPrimaryKey(user.getUserId()).getUserOpenid();
    }



}
