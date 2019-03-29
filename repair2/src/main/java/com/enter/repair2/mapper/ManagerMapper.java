package com.enter.repair2.mapper;

import com.enter.repair2.entity.Manager;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface ManagerMapper extends Mapper<Manager> {
}