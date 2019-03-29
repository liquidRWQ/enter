package com.enter.repair2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @className Rank
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_rank")
public class Rank implements Serializable {

    private static final long serialVersionUID = -2997357417449309253L;
    /**
     * 热修id
     */
    @Id
    @Column(name = "rank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer rankId;

    /**
     * 热修设备类型
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 维修数量
     */
    private Long amount;

}