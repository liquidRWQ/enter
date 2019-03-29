package com.enter.repair2.service;

import com.enter.repair2.DTO.UserInfoDTO;
import com.enter.repair2.entity.UserInfo;
import com.enter.repair2.exception.CheckedException;

import java.util.List;

/**
 * @className UserInfoService
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
public interface UserInfoService {

    void insertOneUserInfo(UserInfoDTO userInfoDTO) throws CheckedException;

    List<UserInfo>  getUserInfosByUserId(UserInfoDTO userInfoDTO) ;

    void updateOneUserInfoByUserInfoId(UserInfoDTO userInfoDTO) throws CheckedException;

    void deleteOneUserInfoByUserInfoId(UserInfoDTO userInfoDTO);
}
