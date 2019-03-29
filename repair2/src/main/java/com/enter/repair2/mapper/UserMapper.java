package com.enter.repair2.mapper;

import com.enter.repair2.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @className UserMapper
 * @auther Liquid
 * @description
 * @date 2018/11/21
 */
@CacheNamespace
@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserMapper extends Mapper<User> {
    Boolean existsWithOpenid(String userOpenid);

    Integer updateByOpenId(User user);

    String selectUserId(String userOpenid);
}