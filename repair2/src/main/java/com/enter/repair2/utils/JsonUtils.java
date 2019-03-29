package com.enter.repair2.utils;

import com.alibaba.fastjson.JSONObject;

/**
* @类名： JsonUtil
*
* @author Liquid
*
* @描述： json转换的工具类
*
* @date   2018/12/28
*/
public class JsonUtils {
    /**
     * @param json json对象
     * @return java.lang.String Json字符串
     * @描述： json对象转换成Json字符串
     * @author Liquid
     * @date 2018/12/26
     */
    public static String toJSONString(Object json) {

        String string = JSONObject.toJSONString(json);
        return string;
    }

    /**
     * @param javabean java对象
     * @return java.lang.Object
     * @描述： java对象转换成json对象
     * @author Liquid
     * @date 2018/12/26
     */
    public static Object toJSON(Object javabean) {

        Object json = JSONObject.toJSON(javabean);
        return json;
    }

    /**
     * @param json  json字符串
     * @param clazz 对象的字节码文件名
     * @return T 指定泛类型
     * @描述： 根据泛型，将json字符串转换并封装成java对象
     * @author Liquid
     * @date 2018/12/26
     */
    public static <T> T toJavaObject(String json, Class<T> clazz) {

        T javaBean = JSONObject.parseObject(json, clazz);
        return javaBean;
    }

    /**
     * @param json json字符串
     * @return com.alibaba.fastjson.JSONObject json的封装对象
     * @描述： 将json字符串转成javaObject
     * @author Liquid
     * @date 2018/12/26
     */
    public static JSONObject toJSONObject(String json) {

        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }

}
