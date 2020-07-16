package com.xufq.practicecore.utils;

import com.xufq.practicecore.security.TokenAuthentication;
import com.xufq.practicecore.vo.UserVo;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class UserInfoUtil {
    public UserVo getUserInfo() {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            return null;
        }
        if (SecurityContextHolder.getContext().getAuthentication() instanceof TokenAuthentication) {
            return (UserVo) SecurityContextHolder.getContext().getAuthentication().getDetails();
        }
        return null;
    }
}
