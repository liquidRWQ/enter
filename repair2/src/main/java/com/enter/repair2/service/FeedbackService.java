package com.enter.repair2.service;

import com.enter.repair2.DTO.FeedbackDTO;
import com.enter.repair2.entity.Feedback;

import java.util.List;

/**
 * @className FeedbackService
 * @auther Liquid
 * @description
 * @date 2018/11/11
 */
public interface FeedbackService {
    void insertOneFeedback(FeedbackDTO feedbackDTO) throws Exception;
    List<Feedback> getAll() ;
}
