package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.exception.BusinessException;
import com.xufq.practicecore.exception.InternalServerErrorException;
import com.xufq.practicecore.utils.EncryptUtil;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.exception.ErrorCode;
import com.xufq.userserver.exception.NotFoundResourceException;
import com.xufq.userserver.exception.SaveOrUpdateException;
import com.xufq.userserver.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @ClassName UserService
 * @Description manage user info
 * @Author fangqiang.xu
 * @Date 8/1/2019 10:13 PM
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int saveUser(UserBo userBo) {
        UserEntity userEntity = UserEntity.builder()
                .accountName(userBo.getAccountName())
                .userName(userBo.getUserName())
                .password(EncryptUtil.encode(userBo.getPassword()))
                .deleted(Constants.UNDELETED)
                .build();
        int saveCount = userDao.saveUser(userEntity);
        if (saveCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_SAVE_ERROR);
        }
        return userEntity.getId();

    }

    public UserVo getUserById(int userId) {
        UserVo userVo = new UserVo();
        UserEntity userEntity = userDao.getUser(UserEntity.builder().id(userId).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        BeanUtils.copyProperties(userEntity, userVo);
        return userVo;
    }

    public UserVo getUserByAccountName(String accountName) {
        UserVo userVo = new UserVo();
        UserEntity userEntity = userDao.getUser(UserEntity.builder().accountName(accountName).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        BeanUtils.copyProperties(userEntity, userVo);
        return userVo;
    }

    public void updateUserInfo(UserBo userBo) {
        UserEntity userEntity = UserEntity.builder().build();
        BeanUtils.copyProperties(userBo, userEntity);
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }

    }

    public void updatePassword(PasswordBo passwordBo){
        if(!StringUtils.equals(passwordBo.getNewPassword(), passwordBo.getConfirmPassword())){
            throw new BusinessException("Password confirm is different with new password!");
        }
        UserEntity paramEntity = UserEntity.builder()
                .accountName(passwordBo.getAccountName())
                .build();
        UserEntity userEntity = userDao.getUser(paramEntity);
        if(Objects.isNull(userEntity) || !EncryptUtil.match(passwordBo.getOldPassword(), userEntity.getPassword())){
            throw new BusinessException("Account name or old password is wrong!");
        }
        userEntity = UserEntity.builder()
                .accountName(passwordBo.getAccountName())
                .password(EncryptUtil.encode(passwordBo.getNewPassword()))
                .build();
        int updateCount = userDao.updateUser(userEntity);
        if(updateCount ==0){
            throw new InternalServerErrorException("Update password failed!");
        }

    }

    public void deleteUser(String accountName){
        UserEntity paramEntity = UserEntity.builder()
                .accountName(accountName)
                .build();
        UserEntity userEntity = userDao.getUser(paramEntity);
        if(Objects.isNull(userEntity)){
            throw new BusinessException("User does not exist!");
        }
        userEntity = UserEntity.builder()
                .accountName(accountName)
                .deleted(Constants.DELETED)
                .build();
        int updateCount = userDao.updateUser(userEntity);
        if(updateCount ==0){
            throw new InternalServerErrorException("Delete user failed!");
        }
    }
}
