package com.enter.repair2.mapper;

import com.enter.repair2.entity.Fault;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@CacheNamespace
@org.apache.ibatis.annotations.Mapper
@Repository
public interface FaultMapper extends Mapper<Fault> {
}