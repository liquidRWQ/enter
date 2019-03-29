package com.enter.repair2.entity;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.TimeUtils;
import com.enter.repair2.utils.IdUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @className Feedback
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@Data
@Table(name = "repair_feedback")
public class Feedback implements Serializable {
    private static final long serialVersionUID = -539320598897426695L;
    /**
     * 意见反馈id
     */
    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String feedbackId;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 查阅状态
     */
    private Boolean status;

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
     * 图片url
     */
    @Column(name = "img_path")
    private String imgPath;

    /**
     * 反馈内容
     */
    private String advice;

    public void setFeedbackIdAndAllTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setCreateTime(currentTime);
        this.setLastUpdateTime(currentTime);
        this.setFeedbackId(IdUtils.getRandomStringId());
    }

    public void refreshLastUpdateTime() throws CheckedException {
        Date currentTime = TimeUtils.getCurrentTime();
        this.setLastUpdateTime(currentTime);
    }

}