package com.xufq.loginserver.service;

import com.xufq.loginserver.bo.LoginBo;
import com.xufq.loginserver.dao.UserDao;
import com.xufq.loginserver.entity.UserEntity;
import com.xufq.loginserver.exception.BusinessException;
import com.xufq.loginserver.exception.UserOrPasswordWrongException;
import com.xufq.loginserver.utils.EncryptUtil;
import com.xufq.loginserver.utils.RedisUtil;
import com.xufq.loginserver.utils.UUIDUtil;
import com.xufq.loginserver.vo.ImageCodeVo;
import com.xufq.loginserver.vo.UserVo;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.MINUTES;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 10:13 PM
 * @Version 1.0
 */
@Log
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {

    @Value("${com.xufq.login.expire-time:15}")
    private long expireTime;

    private final UserDao userDao;
    private final RedisUtil redisUtil;

    public LoginService(UserDao userDao, RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        this.userDao = userDao;
    }

    public String login(ImageCodeVo imageCodeVo, LoginBo loginBo) {
        if (Objects.isNull(imageCodeVo)) {
            throw new BusinessException("验证码无效！");
        }
        if (imageCodeVo.getExpiredDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException("验证码过期！");
        }
        if (!StringUtils.equalsIgnoreCase(imageCodeVo.getImageCode(), loginBo.getCaptchaText())) {
            throw new BusinessException("验证码错误！");
        }
        UserVo user = userDao.getUser(UserEntity.builder()
                .accountName(loginBo.getAccountName())
                .build());
        if (!EncryptUtil.match(loginBo.getPassword(), user.getPassword())) {
            throw new UserOrPasswordWrongException("USER_OR_PASSWORD_WRONG");
        }
        user.setExpiredDate(LocalDateTime.now().plus(expireTime, MINUTES));
        String token = UUIDUtil.getUUID();
        redisUtil.set(getUserInfoKey(token), user);
        return token;
    }

    public UserVo validateUser(String token) {
        return redisUtil.get(getUserInfoKey(token));
    }

    private String getUserInfoKey(String token){
        return "UserInfoKey:"+token;
    }
}
