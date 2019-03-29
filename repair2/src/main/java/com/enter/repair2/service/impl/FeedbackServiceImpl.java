package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.FeedbackDTO;
import com.enter.repair2.entity.Feedback;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.mapper.FeedbackMapper;
import com.enter.repair2.service.FeedbackService;
import com.enter.repair2.utils.StringExcuteUtil;
import com.enter.repair2.utils.TemplateMessageUtils.EnterpriseWeChatMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className FeedbackServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/11/11
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void insertOneFeedback(FeedbackDTO feedbackDTO) throws CheckedException {
        Feedback feedback = feedbackDTO.convertToFeeback();
        feedback.setFeedbackIdAndAllTime();
        feedback.setImgPath(StringExcuteUtil.removeQuotationMark(feedback.getImgPath()));
        feedbackMapper.insertSelective(feedback);
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        sendWeChatTemplateMessage(feedbackDTO);
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackMapper.selectAll();
    }

    private void sendWeChatTemplateMessage(FeedbackDTO feedbackDTO) throws CheckedException {
        EnterpriseWeChatMessageUtils.sendFeedbackToCorporateSector(feedbackDTO);

    }
}
