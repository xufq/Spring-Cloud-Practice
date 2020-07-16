package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.utils.EncryptUtil;
import com.xufq.practicecore.utils.UUIDUtil;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.SaveUserBo;
import com.xufq.userserver.bo.UpdateUserBo;
import com.xufq.userserver.dao.RoleDao;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.dao.UserRoleDao;
import com.xufq.userserver.entity.RoleEntity;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.entity.UserRoleEntity;
import com.xufq.userserver.exception.*;
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

    public String saveUser(SaveUserBo userBo) {
        UserEntity userEntity = UserEntity.builder()
                .uuid(UUIDUtil.getUUID())
                .accountName(userBo.getAccountName())
                .userName(userBo.getUserName())
                .password(EncryptUtil.encode(userBo.getPassword()))
                .deleted(Constants.UNDELETED)
                .version(1)
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
            throw new NotFoundTempResourceException(ErrorCode.ROLE_NOT_FOUND);
        }
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .userUuid(userEntity.getUuid())
                .roleUuid(roleEntity.getUuid())
                .deleted(Constants.UNDELETED)
                .build();
        saveCount = userRoleDao.saveUserRole(userRoleEntity);
        if (saveCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_ROLE_SAVE_ERROR);
        }
        return userEntity.getUuid();
    }

    public UserVo getUserByUuid(String userUuid) {
        UserVo userVo = new UserVo();
        UserEntity userEntity = userDao.getUser(UserEntity.builder().uuid(userUuid).build());
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

    public void updateUserInfo(UpdateUserBo userBo) {
        UserEntity userEntity = userDao.getUser(UserEntity.builder().uuid(userBo.getUuid()).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundTempResourceException(ErrorCode.USER_NOT_FOUND);
        }
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }
        if (StringUtils.isNotEmpty(userBo.getRoleCode())) {
            RoleEntity roleEntity = roleDao.getRole(RoleEntity.builder().roleCode(userBo.getRoleCode()).build());
            if (Objects.isNull(roleEntity)) {
                throw new NotFoundTempResourceException(ErrorCode.ROLE_NOT_FOUND);
            }
            updateCount = userRoleDao.updateUserRole(UserRoleEntity.builder()
                    .userUuid(userEntity.getUuid())
                    .roleUuid(roleEntity.getUuid())
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
                .uuid(passwordBo.getUuid())
                .build();
        UserEntity userEntity = userDao.getUser(paramEntity);
        if (Objects.isNull(userEntity)) {
            throw new NotFoundTempResourceException(ErrorCode.USER_NOT_FOUND);
        } else if (!EncryptUtil.match(passwordBo.getOldPassword(), userEntity.getPassword())) {
            throw new BusinessException(ErrorCode.USERID_PASSWORD_ERROR);
        }
        userEntity = UserEntity.builder()
                .uuid(passwordBo.getUuid())
                .password(EncryptUtil.encode(passwordBo.getNewPassword()))
                .version(passwordBo.getVersion())
                .build();
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.PASSWORD_UPDATE_ERROR);
        }

    }

    public void deleteUser(String uuid) {
        UserEntity userEntity = userDao.getUser(UserEntity.builder().uuid(uuid).build());
        if (Objects.isNull(userEntity)) {
            throw new NotFoundTempResourceException(ErrorCode.USER_NOT_FOUND);
        }
        userEntity.setDeleted(Constants.DELETED);
        int updateCount = userDao.updateUser(userEntity);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_DELETE_ERROR);
        }
        updateCount = userRoleDao.updateUserRole(UserRoleEntity.builder()
                .userUuid(userEntity.getUuid())
                .deleted(Constants.DELETED)
                .build());
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_ROLE_UPDATE_ERROR);
        }
    }
}
