package com.enter.repair2.service;

import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.entity.Order;

import java.util.List;

/**
 * @className OrderService
 * @auther Liquid
 * @description
 * @date 2018/11/14
 */
public interface OrderService {
    void insertOneOrder(OrderDTO orderDTO) throws Exception ;

    List<OrderDTO> getOrderInfo();

    void changeOrderStatusToRepairing(Order order);

    void changeOrderStatusToRepaired(Order order);

    void changeOrderStatusToEvaluating(Order order);

    void changeOrderStatusToFinished(Order order);


}
