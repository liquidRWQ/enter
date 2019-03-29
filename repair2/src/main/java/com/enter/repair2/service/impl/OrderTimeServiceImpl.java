package com.enter.repair2.service.impl;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.service.OrderTimeService;
import com.enter.repair2.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @className OrderTimeServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/11/29
 */

@Service("orderTimeService")
public class OrderTimeServiceImpl implements OrderTimeService{
    @Override
    public Date getOrderTimeRule() throws CheckedException {
        return TimeUtils.getCurrentTime();
    }
}
