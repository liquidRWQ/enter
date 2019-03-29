package com.enter.repair2.controller;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.OrderTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @className OrderTimeController
 * @auther Liquid
 * @description
 * @date 2018/11/29
 */
@RestController
public class OrderTimeController {

    @Autowired
    private OrderTimeService orderTimeService;

    @GetMapping("/getOrderTime")
    public ResultBean getOrderTime() throws CheckedException {

        Date orderTimeRule = orderTimeService.getOrderTimeRule();
        return new ResultBean<Date>(orderTimeRule);
    }
}
