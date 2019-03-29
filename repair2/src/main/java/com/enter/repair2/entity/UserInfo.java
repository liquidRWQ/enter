package com.enter.repair2.entity;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.TimeUtils;
import com.enter.repair2.utils.IdUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @className UserInfo
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_userinfo")
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1633879098609246800L;
    /**
     * 用户信息id
     */
    @Id
    @Column(name = "userinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String userinfoId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 电话
     */
    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 地区
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

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


    public void setUserInfoIdAndAllTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setCreateTime(currentTime);
        this.setLastUpdateTime(currentTime);
        this.setUserinfoId(IdUtils.getRandomStringId());
    }

    public void refreshLastUpdateTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setLastUpdateTime(currentTime);
    }
}