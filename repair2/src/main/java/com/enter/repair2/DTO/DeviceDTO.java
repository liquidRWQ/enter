package com.enter.repair2.DTO;

import com.enter.repair2.DTO.convert.Convertible;
import com.enter.repair2.entity.Device;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @className DeviceDTO
 * @auther Liquid
 * @description
 * @date 2018/12/22
 */
@Data
public class DeviceDTO implements Serializable {

    private static final long serialVersionUID = -2797850494361176773L;
    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private String deviceType;

    private static DeviceConvert deviceConvert;

    static {
        deviceConvert = new DeviceConvert();
    }

    public Device convertToDevice() {
        Device convert = deviceConvert.convertToDO(this);
        return convert;
    }

    public DeviceDTO convertToDeviceDTO(Device device) {
        DeviceDTO convert = deviceConvert.convertToDTO(device);
        return convert;
    }

    public static class DeviceConvert implements Convertible<Device, DeviceDTO> {

        @Override
        public Device convertToDO(DeviceDTO deviceDTO) {
            Device device = new Device();
            BeanUtils.copyProperties(deviceDTO, device);
            return device;
        }

        @Override
        public DeviceDTO convertToDTO(Device device) {
            DeviceDTO deviceDTO = new DeviceDTO();
            BeanUtils.copyProperties(device, deviceDTO);
            return deviceDTO;
        }
    }
}
