package com.enter.repair2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @className RedisUtil
 * @auther Liquid
 * @description
 * @date 2018/12/20
 */
@Component
public class SpringRedisUtil {

    @Qualifier("stringRedisTemplate")
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void appendString(String key, String append) {
        stringRedisTemplate.opsForValue().append(key, append);
    }

    public void setStringWithTimeOut(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
    }

    public String getString(String key) {
        String data = stringRedisTemplate.opsForValue().get(key);
        return data;
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean hasKey(String key) {

        return stringRedisTemplate.hasKey(key);

    }

}
