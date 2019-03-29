package com.enter.repair2.controller;

import com.enter.repair2.DTO.FeedbackDTO;
import com.enter.repair2.entity.Feedback;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className FeedbackController
 * @auther Liquid
 * @description
 * @date 2018/11/11
 */
@Validated
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addFeedback")
    public ResultBean addFeedback(@Validated FeedbackDTO feedbackDTO,BindingResult bindingResult) throws Exception {
        /**
         * @auther Liquid
         *
         * @param [feedback]
         *
         * @description
         *
         * @date 2018/11/11 
         */
        feedbackService.insertOneFeedback(feedbackDTO);
        return new ResultBean<>();
    }

    @GetMapping("/getFeedback")
    public ResultBean getFeedback() {
        /**
         * @auther Liquid
         *
         * @param [feedback]
         *
         * @description
         *
         * @date 2018/11/11
         */
        List<Feedback> feedbacks = feedbackService.getAll();
        return new ResultBean<List<Feedback>>(feedbacks);
    }

}
