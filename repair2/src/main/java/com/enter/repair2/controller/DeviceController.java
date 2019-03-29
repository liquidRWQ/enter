package com.enter.repair2.controller;

import com.enter.repair2.entity.Device;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className DeviceController
 * @auther Liquid
 * @description
 * @date 2018/11/14
 */
@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getAllDeviceType")
    public ResultBean getAllDeviceType() {
        List<Device> devices = deviceService.getDeviceType();
        return new ResultBean<List<Device>>(devices);
    }
}
