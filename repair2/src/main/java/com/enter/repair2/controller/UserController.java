package com.enter.repair2.controller;

import com.enter.repair2.DTO.WeChatLoginDTO;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className UserController
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
@Validated
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultBean login(@Validated WeChatLoginDTO weChatLoginDTO, BindingResult bindingResult) throws Exception {
        String userId = userService.simpleLogin(weChatLoginDTO);
        return new ResultBean<String>(userId);
    }
}
