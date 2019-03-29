package com.enter.repair2.utils;

import lombok.extern.java.Log;

/**
 * @className StringExcuteUtil
 * @auther Liquid
 * @description
 * @date 2018/11/24
 */
@Log
public class StringExcuteUtil {
    public static String  removeQuotationMark(String target){
        String replace = target.replace("\"", "").replace("\'", "");
        log.info("删除引号");
        return replace;
    }

    public static String[]  splitByComma(String target){
        String[] split = target.split(",");
        log.info("根据逗号分割");
        return split;
    }
}
