package com.xufq.userserver.service;

import com.xufq.practicecore.exception.BusinessException;
import com.xufq.practicecore.exception.SessionExpiredException;
import com.xufq.practicecore.utils.RequestUtil;
import com.xufq.userserver.bo.LoginBo;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.constants.UserConstants;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.utils.EncryptUtil;
import com.xufq.userserver.vo.ImageCodeVo;
import com.xufq.userserver.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void validateUser(LoginBo loginBo){

        HttpSession session = RequestUtil.getRequest().getSession();
        if(Objects.isNull(session)){
            throw new SessionExpiredException();
        }
        ImageCodeVo imageCodeVo = (ImageCodeVo)session.getAttribute(UserConstants.SESSION_CAPTCHA_KEY);
        if(imageCodeVo.getExpiredDate().isBefore(LocalDateTime.now())){
            throw new BusinessException();
        }
        if(!StringUtils.equals(imageCodeVo.getImageCode(),loginBo.getCaptchaText())){
            throw new BusinessException();
        }
        UserEntity user = userDao.getUser(UserEntity.builder()
                .accountName(loginBo.getAccountName())
                .password(EncryptUtil.encryptSHA(loginBo.getPassword()))
                .build());
        if(Objects.isNull(user)){
            throw new BusinessException();
        }
        session.setAttribute(UserConstants.SESSION_USER_KEY, user);
    }

    public int saveUser(UserBo userBo){
        UserEntity userEntity = UserEntity.builder()
                .accountName(userBo.getAccountName())
                .userName(userBo.getUserName())
                .password(EncryptUtil.encryptSHA(userBo.getPassword()))
                .deleted("N")
                .build();
        int saveCount = userDao.saveUser(userEntity);
        if(saveCount == 0){
            throw new BusinessException();
        }
        return userEntity.getId();

    }

    public UserVo getUserById(int userId){
        return UserVo.builder()
                .id(userId)
                .userName(getRequestURL())
                .build();
    }

    private String getRequestURL(){
        return RequestUtil.getRequest().getRequestURL().toString();
    }
}
