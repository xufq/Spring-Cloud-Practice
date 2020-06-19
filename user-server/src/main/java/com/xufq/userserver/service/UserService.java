package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.utils.EncryptUtil;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.dao.RoleDao;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.dao.UserRoleDao;
import com.xufq.userserver.entity.RoleEntity;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.entity.UserRoleEntity;
import com.xufq.userserver.exception.ErrorCode;
import com.xufq.userserver.exception.NotFoundResourceException;
import com.xufq.userserver.exception.SaveOrUpdateException;
import com.xufq.userserver.exception.BusinessException;
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
    private final RoleDao roleDao;
    private final UserRoleDao userRoleDao;

    public UserService(UserDao userDao, RoleDao roleDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userRoleDao = userRoleDao;
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
        RoleEntity param = RoleEntity.builder()
                .roleCode(userBo.getRoleCode())
                .build();
        RoleEntity roleEntity = roleDao.getRole(param);
        if (Objects.isNull(roleEntity)) {
            throw new NotFoundResourceException(ErrorCode.ROLE_NOT_FOUND);
        }
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .userId(userEntity.getId())
                .roleId(roleEntity.getId())
                .deleted(Constants.UNDELETED)
                .build();
        saveCount = userRoleDao.saveUserRole(userRoleEntity);
        if (saveCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_ROLE_SAVE_ERROR);
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
        UserEntity userEntity = userDao.getUser(UserEntity.builder().accountName(userBo.getAccountName()).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }
        if (StringUtils.isNotEmpty(userBo.getRoleCode())) {
            RoleEntity roleEntity = roleDao.getRole(RoleEntity.builder().roleCode(userBo.getRoleCode()).build());
            if (Objects.isNull(roleEntity)) {
                throw new NotFoundResourceException(ErrorCode.ROLE_NOT_FOUND);
            }
            updateCount = userRoleDao.updateUserRole(UserRoleEntity.builder()
                    .userId(userEntity.getId())
                    .roleId(roleEntity.getId())
                    .build());
            if (updateCount == 0) {
                throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
            }
        }
    }

    public void updatePassword(PasswordBo passwordBo) {
        if (!StringUtils.equals(passwordBo.getNewPassword(), passwordBo.getConfirmPassword())) {
            throw new BusinessException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }
        UserEntity paramEntity = UserEntity.builder()
                .accountName(passwordBo.getAccountName())
                .build();
        UserEntity userEntity = userDao.getUser(paramEntity);
        if (Objects.isNull(userEntity) || !EncryptUtil.match(passwordBo.getOldPassword(), userEntity.getPassword())) {
            throw new BusinessException(ErrorCode.USERID_PASSWORD_ERROR);
        }
        userEntity = UserEntity.builder()
                .accountName(passwordBo.getAccountName())
                .password(EncryptUtil.encode(passwordBo.getNewPassword()))
                .build();
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.PASSWORD_UPDATE_ERROR);
        }

    }

    public void deleteUser(String accountName) {
        UserEntity userEntity = userDao.getUser(UserEntity.builder().accountName(accountName).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        userEntity.setDeleted(Constants.DELETED);
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }
        updateCount = userRoleDao.updateUserRole(UserRoleEntity.builder()
                .userId(userEntity.getId())
                .deleted(Constants.DELETED)
                .build());
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_ROLE_UPDATE_ERROR);
        }
    }
}
