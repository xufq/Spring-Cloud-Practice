package com.xufq.practicecore.client;

import com.xufq.practicecore.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName LoginFeignClient
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/2/2020 08:45 PM
 * @Version 1.0
 */
@FeignClient(name = "login-server")
public interface LoginFeignClient {

    @GetMapping("/login/validate")
    UserVo validate();
}
