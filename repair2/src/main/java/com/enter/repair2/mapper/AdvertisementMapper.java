package com.enter.repair2.mapper;

import com.enter.repair2.entity.Advertisement;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/*import tk.mybatis.mapper.common.Mapper;*/

/**
*@className AdvertisementMapper
*
*@auther Liquid
*
*@description 
*
*@date   2018/11/21 
*/
@CacheNamespace
@org.apache.ibatis.annotations.Mapper
@Repository
public interface AdvertisementMapper extends Mapper<Advertisement> {
}