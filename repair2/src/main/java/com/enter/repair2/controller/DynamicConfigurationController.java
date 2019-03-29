package com.enter.repair2.controller;

import com.enter.repair2.result.ResultBean;
import com.enter.repair2.utils.DynamicConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liquid
 * @类名： DynamicConfigurationController
 * @描述：
 * @date 2018/12/30
 */
@RestController
public class DynamicConfigurationController {

    @Autowired
    private DynamicConfigUtils dynamicConfigUtils;

    @GetMapping("/settings")
    public ResultBean setMangerPublicOpenId() {
        String result = dynamicConfigUtils.setMangerPublicOpenId();
        return new ResultBean<String>(result);
    }
}
