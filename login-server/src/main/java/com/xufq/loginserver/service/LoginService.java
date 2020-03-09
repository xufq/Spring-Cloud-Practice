package com.xufq.loginserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xufq.loginserver.bo.LoginBo;
import com.xufq.loginserver.dao.UserDao;
import com.xufq.loginserver.entity.UserEntity;
import com.xufq.loginserver.utils.EncryptUtil;
import com.xufq.loginserver.utils.RequestUtil;
import com.xufq.loginserver.vo.ImageCodeVo;
import com.xufq.loginserver.vo.UserVo;
import com.xufq.practicecore.exception.BusinessException;
import com.xufq.practicecore.exception.InternalException;
import com.xufq.practicecore.exception.SessionExpiredException;
import com.xufq.practicecore.security.SessionConstants;
import com.xufq.practicecore.utils.ObjectMapperUtil;
import lombok.extern.java.Log;
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
@Log
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {

    private static final String UNDELETED = "N";
    private final UserDao userDao;

    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void login(LoginBo loginBo) {

        HttpSession session = RequestUtil.getRequest().getSession();
        if (Objects.isNull(session)) {
            throw new SessionExpiredException();
        }
        try {
            Object imageObject = session.getAttribute(SessionConstants.SESSION_CAPTCHA_KEY);
            if (Objects.isNull(imageObject)) {
                // TODO
                throw new SessionExpiredException();
            }
            ImageCodeVo imageCodeVo = ObjectMapperUtil.getMapper().readValue(imageObject.toString(), ImageCodeVo.class);
            if (imageCodeVo.getExpiredDate().isBefore(LocalDateTime.now())) {
                // TODO
                throw new SessionExpiredException();
            }
            if (!StringUtils.equals(imageCodeVo.getImageCode(), loginBo.getCaptchaText())) {
                // TODO
                throw new BusinessException();
            }
            UserVo user = userDao.getUser(UserEntity.builder()
                    .accountName(loginBo.getAccountName())
                    .password(EncryptUtil.encryptSHA(loginBo.getPassword()))
                    .build());
            if (Objects.isNull(user)) {
                // TODO
                throw new BusinessException();
            }
            user.setExpiredDate(LocalDateTime.now());
            session.setAttribute(SessionConstants.SESSION_USER_KEY, ObjectMapperUtil.getMapper().writeValueAsString(user));

        } catch (JsonProcessingException ex) {
            // TODO log error message
            log.warning("");
            throw new InternalException();
        }
    }

    public UserVo validateUser() {
        HttpSession session = RequestUtil.getRequest().getSession();
        if (Objects.isNull(session)) {
            throw new SessionExpiredException();
        }
        try {
            Object userObject = session.getAttribute(SessionConstants.SESSION_USER_KEY);
            if (Objects.isNull(userObject)) {
                throw new SessionExpiredException();
            }
            return ObjectMapperUtil.getMapper().readValue(userObject.toString(), UserVo.class);
        } catch (JsonProcessingException ex) {
            throw new InternalException();
        }
    }

    private String getRequestURL() {
        return RequestUtil.getRequest().getRequestURL().toString();
    }
}
