package com.xufq.practicecore.client;

import com.xufq.practicecore.config.LoginFeignClientConfiguration;
import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.vo.UserVo;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginFeignClient
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/2/2020 08:45 PM
 * @Version 1.0
 */
@FeignClient(name = "login-server", configuration = {LoginFeignClientConfiguration.class})
@RequestMapping(value = "/login")
public interface LoginFeignClient {

    @GetMapping(value = "/validate")
    UserVo validate(@RequestHeader(Constants.TOKEN) String token);
}
