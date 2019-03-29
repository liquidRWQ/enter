package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.DeviceDTO;
import com.enter.repair2.entity.Device;
import com.enter.repair2.mapper.DeviceMapper;
import com.enter.repair2.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @className DeviceServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/12/22
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> getDeviceType() {
        LinkedList<DeviceDTO> deviceDTOs = new LinkedList<>();
        DeviceDTO deviceDTO = new DeviceDTO();
        List<Device> devices = deviceMapper.selectAll();

        return devices;


    }
}
