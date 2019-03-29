package com.enter.repair2.service;

import com.enter.repair2.DTO.ManagerDTO;
import com.enter.repair2.entity.Manager;
import com.enter.repair2.exception.CheckedException;

import java.util.List;

/**
 * @author Liquid
 * @类名： MangerService
 * @描述：
 * @date 2018/12/30
 */
public interface ManagerService {
     void insertOneManagerPublicId(ManagerDTO managerDTO) throws CheckedException;

     List<Manager> getAllManagers()  ;
}
