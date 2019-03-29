package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.ManagerDTO;
import com.enter.repair2.entity.Manager;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.mapper.ManagerMapper;
import com.enter.repair2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liquid
 * @类名： ManagerServiceImpl
 * @描述：
 * @date 2018/12/30
 */
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public void insertOneManagerPublicId(ManagerDTO managerDTO) throws CheckedException {
        Manager manager = managerDTO.convertToManager();
        if (managerMapper.selectOne(manager)==null){
            manager.setIdAndAllTime();
            manager.refreshLastUpdateTime();
            managerMapper.insertSelective(manager);
        }else {
            throw new UnCheckedException("该管理员已经存在");
        }


    }

    @Override
    public List<Manager> getAllManagers() {

        return managerMapper.selectAll();
    }
}
