package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.OrderDTO;
import com.enter.repair2.entity.Order;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.mapper.OrderMapper;
import com.enter.repair2.mapper.UserMapper;
import com.enter.repair2.service.OrderService;
import com.enter.repair2.utils.TemplateMessageUtils.EnterpriseWeChatMessageUtils;
import com.enter.repair2.utils.TemplateMessageUtils.WeAppMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className OrderServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/11/14
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOneOrder(OrderDTO orderDTO) throws Exception {
        Order order = orderDTO.convertToOrder();
        order.setOrderIdAndAllTime();
        orderDTO.setOrderId(order.getOrderId());
        orderMapper.insertSelective(order);
        orderDTO.setUserOpenId(userMapper.selectByPrimaryKey(orderDTO.getUserId()).getUserOpenid());
        sendWeChatTemplateMessage(orderDTO);
    }

    @Override
    public List<OrderDTO> getOrderInfo() {

        return null;
    }

    @Override
    public void changeOrderStatusToRepairing(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        if (i == 0) {
            throw new UnCheckedException("订单不存在");
        }
    }

    @Override
    public void changeOrderStatusToRepaired(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        if (i == 0) {
            throw new UnCheckedException("订单不存在");
        }
    }

    @Override
    public void changeOrderStatusToEvaluating(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        if (i == 0) {
            throw new UnCheckedException("订单不存在");
        }
    }

    @Override
    public void changeOrderStatusToFinished(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        if (i == 0) {
            throw new UnCheckedException("订单不存在");
        }
    }

    private void sendWeChatTemplateMessage(OrderDTO orderDTO) throws CheckedException {
        EnterpriseWeChatMessageUtils.sendOrderToCorporateSector(orderDTO);
        WeAppMessageUtil.sendMessageToUser(orderDTO);

    }
}
