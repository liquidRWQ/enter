package com.enter.repair2.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @className LogAndException
 * @auther Liquid
 * @description
 * @date 2018/12/24
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogAndException {
    public boolean isEnable() default true;
}
