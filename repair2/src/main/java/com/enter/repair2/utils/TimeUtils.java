package com.enter.repair2.utils;

import com.enter.repair2.exception.CheckedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类名： CurrentTimeUtil
 *
 * @author Liquid
 * <p>
 * 描述：获取系统当前时间
 * @date 2018/12/26
 */
public class TimeUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";

    /**
     * @param
     * @return Date当前日期
     * @描述： 获取当前格式化后的的时间
     * @author Liquid
     * @date 2018/12/26
     */
    public static Date getCurrentTime() throws CheckedException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.YYYY_MM_DD_HH_MM_SS);
        Date date = calendar.getTime();
        String format = simpleDateFormat.format(date);
        Date parse;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            throw new CheckedException("格式化时间失败：" + e.toString());
        }
        return parse;
    }

    /**
     * @param
     * @return java.lang.String 时间的字符串
     * @throws null
     * @author Liquid
     * @描述： 获取当前格式化后的的时间的字符串
     * @date 2018/12/30
     */
    public static String getCurrentTimeString() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.YYYY_MM_DD_HH_MM_SS);
        Date date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param date 日期对象
     * @return java.lang.String 格式化后的字符串
     * @描述： 将日期对象格式默认格式化成字符串
     * @author Liquid
     * @date 2018/12/28
     */
    public static String dateDefaultFormatToString(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.YYYY_MM_DD_HH_MM_SS);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * @param date 日期对象
     * @return java.util.Date 格式化后的日期对象
     * @描述： 将日期对象格式默认格式化成日期对象
     * @author Liquid
     * @date 2018/12/28
     */
    public static Date dateDefaultFormatToDate(Date date) throws CheckedException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.YYYY_MM_DD_HH_MM_SS);
        String format = simpleDateFormat.format(date);
        Date parse;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            throw new CheckedException("格式化时间失败：" + e.toString());
        }
        return parse;
    }

    /**
     * @param format 日期格式
     * @return java.util.Date 格式化后的Date对象
     * @描述： 根据时间格式获取当前时间Date
     * @author Liquid
     * @date 2018/12/28
     */
    public static Date getCurrentTimeByFormat(String format) throws CheckedException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = calendar.getTime();
        format = simpleDateFormat.format(date);
        Date parse;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            throw new CheckedException("格式化时间失败：" + e.toString());
        }
        return parse;
    }

    /**
     * @param date   日期对象
     * @param format 日期格式
     * @return java.lang.String 转换后的日期字符串
     * @author Liquid
     * @描述： 根据格式转换Date为日期字符串
     * @date 2018/12/28
     */
    public static String dateFormatToStringByFormat(Date date, String format) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * @param date   日期对象
     * @param format 日期格式
     * @return java.util.Date 格式化后的日期
     * @author Liquid
     * @描述： 根据格式将Date对象转换成格式化后的Date
     * @date 2018/12/28
     */
    public static Date dateFormatToDateByFormat(Date date, String format) throws CheckedException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        format = simpleDateFormat.format(date);
        Date parse;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            throw new CheckedException("格式化时间失败：" + e.toString());
        }
        return parse;
    }

    /**
     * @param date   日期对象
     * @param format 日期格式
     * @return java.util.Date 转换后的Date对象
     * @author Liquid
     * @描述： 根据格式将日期字符串转成Date对象
     * @date 2018/12/28
     */
    public static Date stringFormatDateByFormat(String date, String format) throws CheckedException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parseDate;
        try {
            parseDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new CheckedException("格式化时间失败：" + e.toString());
        }
        return parseDate;
    }

    /**
     * @param time 指定时间距离当前时间的中文信息
     * @return java.lang.String
     * @author Liquid
     * @描述： 获取指定时间距离当前时间的中文信息
     * @date 2018/12/28
     */
    public static String getLnow(long time) {

        Calendar calendar = Calendar.getInstance();
        long timel = calendar.getTimeInMillis() - time;
        if (timel / 1000 < 60) {
            return "1分钟以内";
        } else if (timel / 1000 / 60 < 60) {
            return timel / 1000 / 60 + "分钟前";
        } else if (timel / 1000 / 60 / 60 < 24) {
            return timel / 1000 / 60 / 60 + "小时前";
        } else {
            return timel / 1000 / 60 / 60 / 24 + "天前";
        }
    }

}
