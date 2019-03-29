package com.enter.repair2.controller;

import com.enter.repair2.DTO.UserInfoDTO;
import com.enter.repair2.entity.UserInfo;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className AddressController
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
@Validated
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/addUserInfo")
    public ResultBean addUserInfo(UserInfoDTO userInfoDTO) throws CheckedException {
        System.out.println("userInfoDTO = " + userInfoDTO);
        userInfoService.insertOneUserInfo(userInfoDTO);
        return new ResultBean<>();
    }

    @GetMapping("/getUserInfoByUserId")
    public ResultBean getUserInfoByUserId(UserInfoDTO userInfoDTO) {
        List<UserInfo> userInfosByUserId = userInfoService.getUserInfosByUserId(userInfoDTO);
        return new ResultBean<List<UserInfo>>(userInfosByUserId);
    }

    @PutMapping("/updateUserInfo")
    public ResultBean updateUserInfo(UserInfoDTO userInfoDTO) throws CheckedException {
        userInfoService.updateOneUserInfoByUserInfoId(userInfoDTO);
        return new ResultBean<>();
    }

    @DeleteMapping("/deleteUserInfo")
    public ResultBean deleteUserInfo(UserInfoDTO userInfoDTO) {
        userInfoService.deleteOneUserInfoByUserInfoId(userInfoDTO);
        return new ResultBean<>();
    }
}
