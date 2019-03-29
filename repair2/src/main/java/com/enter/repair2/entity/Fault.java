package com.enter.repair2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @className Fault
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_fault")
public class Fault implements Serializable{

    private static final long serialVersionUID = 5812708041089322206L;
    /**
     * 故障id
     */
    @Id
    @Column(name = "fault_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String faultId;

    /**
     * 关联设备id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 故障类型
     */
    @Column(name = "fault_type")
    private String faultType;

    /**
     * 维修定价
     */
    @Column(name = "repair_price")
    private Double repairPrice;

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

}