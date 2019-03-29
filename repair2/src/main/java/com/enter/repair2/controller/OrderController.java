package com.enter.repair2.controller;

import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className OrderController
 * @auther Liquid
 * @description
 * @date 2018/11/14
 */
@Validated
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResultBean addOrder(@Validated OrderDTO orderDTO ,BindingResult bindingResult) throws Exception {
        log.info(orderDTO.toString());
        orderService.insertOneOrder(orderDTO);
        return new ResultBean<>();
    }

    @GetMapping("/getAllOrderInfo")
    public ResultBean getAllOrderInfo()  {
        /**
         * @auther Liquid
         *
         * @param []
         *
         * @description
         *
         * @date 2018/11/14 
         */
        List<OrderDTO> orderInfos = orderService.getOrderInfo();
        return new ResultBean<List<OrderDTO>>(orderInfos);
    }


}
