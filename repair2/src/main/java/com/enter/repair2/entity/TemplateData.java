package com.enter.repair2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liquid
 * @类名： TemplateData
 * @描述：
 * @date 2018/12/29
 */
@Data
public class TemplateData implements Serializable {

    private static final long serialVersionUID = -6795296713872687186L;
    private String value;
    private String color;
}
