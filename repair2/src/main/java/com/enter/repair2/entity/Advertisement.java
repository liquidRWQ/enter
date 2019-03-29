package com.enter.repair2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @className AdvertisementDTO
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_advertisement")
public class Advertisement implements Serializable {
    private static final long serialVersionUID = -4441285580031360470L;
    /**
     * 广告id
     */
    @Id
    @Column(name = "advertisement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String advertisementId;

    /**
     * 广告名称
     */
    @Column(name = "advertisement_name")
    private String advertisementName;

    /**
     * 广告图片路径
     */
    @Column(name = "advertisement_img")
    private String advertisementImg;

    /**
     * 广告开始时间
     */
    @Column(name = "advertisement_start_time")
    private Date advertisementStartTime;

    /**
     * 广告结束时间
     */
    @Column(name = "advertisement_end_time")
    private Date advertisementEndTime;

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