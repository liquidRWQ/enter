package com.enter.repair2.entity;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.IdUtils;
import com.enter.repair2.utils.TimeUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @类名： Manager
*
* @author Liquid
*
* @描述：
*
* @date   2018/12/30
*/
@Data
@Table(name = "manager")
public class Manager implements Serializable{
    private static final long serialVersionUID = 8043835400058172165L;
    /**
     * 管理员表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String id;

    /**
     * 管理员的小程序openid
     */
    @Column(name = "app_open_id")
    private String appOpenId;

    /**
     * 管理员的公众号openid
     */
    @Column(name = "public_open_id")
    private String publicOpenId;

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

    public void setIdAndAllTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setCreateTime(currentTime);
        this.setLastUpdateTime(currentTime);
        this.setId(IdUtils.getRandomStringId());
    }

    public void refreshLastUpdateTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setLastUpdateTime(currentTime);
    }

}