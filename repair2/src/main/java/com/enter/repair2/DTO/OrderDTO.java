package com.enter.repair2.DTO;

import com.enter.repair2.DTO.convert.Convertible;
import com.enter.repair2.entity.Order;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @className OrderDTO
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -8045478386356165140L;

    private String orderId;

    @NotBlank
    private String userId;

    private String userOpenId;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String detailAddress;

    @NotBlank
    private String deviceType;

    @NotBlank
    private String faultType;

    private Date repairTime;

    private Date orderTime;

    private String userRemark;

    private String repairWay;

    @NotBlank
    private Double price;

    private String img;

    private String detail;

    @NotNull
    private String[] formIds;

    private static OrderConvert orderConvert;

    static {
        orderConvert = new OrderConvert();
    }

    public Order convertToOrder() {
        Order convert = orderConvert.convertToDO(this);
        return convert;
    }

    public OrderDTO convertToOrderDTO(Order order) {
        OrderDTO convert = orderConvert.convertToDTO(order);
        return convert;
    }

    public static class OrderConvert implements Convertible<Order, OrderDTO> {

        @Override
        public Order convertToDO(OrderDTO orderDTO) {
            Order order = new Order();
            BeanUtils.copyProperties(orderDTO, order);
            return order;
        }

        @Override
        public OrderDTO convertToDTO(Order order) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(order, orderDTO);
            return orderDTO;
        }
    }

}
