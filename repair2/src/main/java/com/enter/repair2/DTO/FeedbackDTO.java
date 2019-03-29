package com.enter.repair2.DTO;

import com.enter.repair2.DTO.convert.Convertible;
import com.enter.repair2.entity.Feedback;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 *
 * @className FeedbackDTO
 * @auther Liquid
 * @description
 * @date 2018/11/29
 */
@Data
public class FeedbackDTO implements Serializable{
    private static final long serialVersionUID = -652259462528570195L;
    /**
     * 意见反馈id
     */
    private String feedbackId;

    /**
     * 关联用户id
     */
    @NotBlank
    private String userId;

    /**
     * 用户openid
     */
    private String userOpenId;

    /**
     * 联系电话
     */
    @Pattern(regexp = "^1\\d{10}$")
    private String telephone;

    /**
     * 查阅状态
     */
    private Boolean status;

    /**
     * 图片url
     */
    private String imgPath;

    /**
     * 反馈内容
     */
    @NotBlank
    private String advice;

    @NotNull
    private String[] formIds;

    private static FeedbackConvert feedbackConvert;

    static {
        feedbackConvert = new FeedbackConvert();
    }

    public Feedback convertToFeeback() {
        Feedback feedback = feedbackConvert.convertToDO(this);
        return feedback;
    }

    public FeedbackDTO convertToFeedbackDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = feedbackConvert.convertToDTO(feedback);
        return feedbackDTO;
    }

    public static class FeedbackConvert implements Convertible<Feedback, FeedbackDTO> {

        @Override
        public Feedback convertToDO(FeedbackDTO feedbackDTO) {
            Feedback feedback = new Feedback();
            BeanUtils.copyProperties(feedbackDTO, feedback);
            return feedback;
        }

        @Override
        public FeedbackDTO convertToDTO(Feedback feedback) {
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            BeanUtils.copyProperties(feedback, feedbackDTO);
            return feedbackDTO;
        }
    }
}
