package com.enter.repair2.service;

import com.enter.repair2.exception.CheckedException;

import java.util.Date;

/**
 * @className OrderTimeService
 * @auther Liquid
 * @description
 * @date 2018/11/29
 */
public interface OrderTimeService {

    Date getOrderTimeRule() throws CheckedException;
}
