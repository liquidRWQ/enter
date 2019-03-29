package com.enter.repair2.entity;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.TimeUtils;
import com.enter.repair2.utils.IdUtils;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @className Order
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_order")
public class Order {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String orderId;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 关联地址
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 故障类型
     */
    @Column(name = "fault_type")
    private String faultType;

    /**
     * 订单价格
     */
    private Double price;

    /**
     * 故障描述
     */
    private String detail;

    /**
     * 图片信息
     */
    private String img;

    /**
     * 上门(维修)时间
     */
    @Column(name = "repair_time")
    private Date repairTime;

    /**
     * 订单提交时间
     */
    @Column(name = "order_time")
    private Date orderTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 用户留言
     */
    @Column(name = "user_remark")
    private String userRemark;

    public void setOrderIdAndAllTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setOrderTime(currentTime);
        this.setCreateTime(currentTime);
        this.setLastUpdateTime(currentTime);
        this.setOrderId(IdUtils.getRandomStringId());
    }

    public void refreshLastUpdateTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setLastUpdateTime(currentTime);
    }
}