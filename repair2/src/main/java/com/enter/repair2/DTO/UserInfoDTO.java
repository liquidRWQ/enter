package com.enter.repair2.DTO;

import com.enter.repair2.DTO.convert.Convertible;
import com.enter.repair2.entity.UserInfo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Liquid
 * @类名： ${name}
 * @描述：
 * @date ${date}
 */
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -287095665507138981L;

    /**
     * 用户信息id
     */
    private String userinfoId;
    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    /**
     * 名称
     */
    private String userName;

    /**
     * 电话
     */
    @Pattern(regexp = "^1\\d{10}$")
    private String userPhoneNumber;

    /**
     * 省
     */
    @NotBlank
    private String province;

    /**
     * 市
     */
    @NotBlank
    private String city;

    /**
     * 地区
     */
    @NotBlank
    private String district;

    /**
     * 详细地址
     */
    @NotBlank
    private String address;

    private static UserInfoConvert UserInfoConvert;

    static {
        UserInfoConvert = new UserInfoConvert();
    }

    public UserInfo convertToUserInfo() {
        UserInfo convert = UserInfoConvert.convertToDO(this);
        return convert;
    }

    public UserInfoDTO convertToUserInfoDTO(UserInfo userInfo) {
        UserInfoDTO convert = UserInfoConvert.convertToDTO(userInfo);
        return convert;
    }

    public static class UserInfoConvert implements Convertible<UserInfo, UserInfoDTO> {

        @Override
        public UserInfo convertToDO(UserInfoDTO userInfoDTO) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userInfoDTO, userInfo);
            return userInfo;
        }

        @Override
        public UserInfoDTO convertToDTO(UserInfo userInfo) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(userInfo, userInfoDTO);
            return userInfoDTO;
        }
    }
}
