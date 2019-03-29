package com.enter.repair2.utils;

import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.entity.Manager;
import com.enter.repair2.service.ManagerService;

import java.util.List;

/**
 * @author Liquid
 * @类名： DynamicConfigUtils
 * @描述：
 * @date 2018/12/30
 */

public class DynamicConfigUtils {

    private ManagerService managerService;

    public DynamicConfigUtils(ManagerService managerService) {
        this.managerService = managerService;
    }

    public  String setMangerPublicOpenId() {
        List<Manager> managers = managerService.getAllManagers();
        StringBuilder stringBuilder = new StringBuilder();
        int index=0;
        for (Manager manager : managers) {
            String publicOpenId = manager.getPublicOpenId();
            RepairAppConfig.managerPublicOpenIds[index++]=publicOpenId;
            stringBuilder.append("员工")
                    .append(index)
                    .append(":")
                    .append(publicOpenId)
                    .append("  ");
        }
        RepairAppConfig.managerCount=index;
        return "重新部署工作人员成功"+stringBuilder.toString();
    }
}
