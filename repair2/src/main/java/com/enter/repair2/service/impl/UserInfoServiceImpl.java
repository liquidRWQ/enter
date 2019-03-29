package com.enter.repair2.service.impl;

import com.enter.repair2.DTO.UserInfoDTO;
import com.enter.repair2.entity.UserInfo;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.mapper.UserInfoMapper;
import com.enter.repair2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className UserInfoServiceImpl
 * @auther Liquid
 * @description
 * @date 2018/11/12
 */
@Service("addressService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * @param userInfoDTO
     * @return void
     * @author Liquid
     * @描述：
     * @date 2018/12/28
     */
    @Override
    public void insertOneUserInfo(UserInfoDTO userInfoDTO) throws CheckedException {

        UserInfo userInfo = userInfoDTO.convertToUserInfo();
        System.out.println("userInfo = " + userInfo);
        userInfo.setUserInfoIdAndAllTime();
        userInfoMapper.insert(userInfo);
    }

    @Override
    public List<UserInfo> getUserInfosByUserId(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoDTO.convertToUserInfo();
        List<UserInfo> userInfos = userInfoMapper.select(userInfo);
        if (userInfos.size() == 0) {
            throw new UnCheckedException("您还没有填写地址信息");
        }
        return userInfos;
    }

    @Override
    public void updateOneUserInfoByUserInfoId(UserInfoDTO userInfoDTO) throws CheckedException {
        UserInfo userInfo = userInfoDTO.convertToUserInfo();
        userInfo.refreshLastUpdateTime();
        int update = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (update == 0) {
            throw new UnCheckedException("该地址已经删除");
        }
    }

    @Override
    public void deleteOneUserInfoByUserInfoId(UserInfoDTO userInfoDTO) {
        String userinfoId = userInfoDTO.getUserinfoId();
        int delete = userInfoMapper.deleteByPrimaryKey(userinfoId);
        if (delete == 0) {
            throw new UnCheckedException("该地址已经删除");
        }
    }

}
