package com.enter.repair2.DTO;

import com.enter.repair2.DTO.convert.Convertible;
import com.enter.repair2.entity.Manager;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author Liquid
 * @类名： ManagerDTO
 * @描述：
 * @date 2018/12/30
 */
@Data
public class ManagerDTO implements Serializable {

    private static final long serialVersionUID = 1742157265144951831L;

    /**
     * 管理员表id
     */
    private String id;

    /**
     * 管理员的小程序openid
     */
    private String appOpenId;

    /**
     * 管理员的公众号openid
     */
    private String publicOpenId;

    private static ManagerConvert managerConvert;

    static {
        managerConvert = new ManagerConvert();
    }

    public Manager convertToManager() {
        Manager manager = managerConvert.convertToDO(this);
        return manager;
    }

    public ManagerDTO convertToManagerDTO(Manager manager) {
        ManagerDTO managerDTO = managerConvert.convertToDTO(manager);
        return managerDTO;
    }

    public static class ManagerConvert implements Convertible<Manager, ManagerDTO> {

        @Override
        public Manager convertToDO(ManagerDTO managerDTO) {
            Manager manager = new Manager();
            BeanUtils.copyProperties(managerDTO, manager);
            return manager;
        }

        @Override
        public ManagerDTO convertToDTO(Manager manager) {
            ManagerDTO managerDTO = new ManagerDTO();
            BeanUtils.copyProperties(manager, managerDTO);
            return managerDTO;
        }
    }
}
