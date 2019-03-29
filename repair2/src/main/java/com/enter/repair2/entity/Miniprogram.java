package com.enter.repair2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liquid
 * @类名： Miniprogram
 * @描述：
 * @date 2018/12/29
 */
@Data
public class Miniprogram  implements Serializable{
    private static final long serialVersionUID = 7944295353530254842L;

    String appid;

    String pagepath;
}
