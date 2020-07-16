package com.xufq.loginserver.service;

import com.xufq.loginserver.exception.InternalException;
import com.xufq.loginserver.utils.RedisUtil;
import com.xufq.loginserver.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class LogoutService {

    private final RedisUtil redisUtil;

    public LogoutService(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void logout(String token) {
        UserVo userVo = redisUtil.get(redisUtil.getUserInfoKey(token));
        if (Objects.isNull(userVo)) {
            throw new InternalException("User has logouted");
        }
        boolean result = redisUtil.del(redisUtil.getUserInfoKey(token));
        if (!result) {
            log.warn("Delete token Failed!");
        }
    }
}
