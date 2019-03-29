package com.enter.repair2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @className Device
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_device")
public class Device implements Serializable{
    private static final long serialVersionUID = -5953191693982968434L;
    /**
     * 设备Id
     */
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String deviceId;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

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