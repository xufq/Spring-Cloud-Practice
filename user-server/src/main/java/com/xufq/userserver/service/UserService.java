package com.xufq.userserver.service;

import com.xufq.practicecore.exception.BusinessException;
import com.xufq.practicecore.utils.RequestUtil;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.utils.EncryptUtil;
import com.xufq.userserver.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 10:13 PM
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    public static final String UNDELETED = "N";
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int saveUser(UserBo userBo){
        UserEntity userEntity = UserEntity.builder()
                .accountName(userBo.getAccountName())
                .userName(userBo.getUserName())
                .password(EncryptUtil.encryptSHA(userBo.getPassword()))
                .deleted(UNDELETED)
                .build();
        int saveCount = userDao.saveUser(userEntity);
        if(saveCount == 0){
            throw new BusinessException();
        }
        return userEntity.getId();

    }

    public UserVo getUserById(int userId){
        UserVo userVo = new UserVo();
        UserEntity userEntity = userDao.getUser(UserEntity.builder().id(userId).build());
        if(Objects.isNull(userEntity)){
            throw new BusinessException("User does not exist.");
        }
        BeanUtils.copyProperties(userEntity, userVo);
        return userVo;
    }

    private String getRequestURL(){
        return RequestUtil.getRequest().getRequestURL().toString();
    }
}
