package com.xufq.userserver.service;

import com.xufq.userserver.utils.RequestUtil;
import com.xufq.userserver.vo.UserVo;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 10:13 PM
 * @Version 1.0
 */
@Service
public class UserService {

    public UserVo getUserById(String userId){
        return UserVo.builder()
                .userId(userId)
                .userName(getRequestURL())
                .build();
    }

    private String getRequestURL(){
        return RequestUtil.getRequest().getRequestURL().toString();
    }
}
