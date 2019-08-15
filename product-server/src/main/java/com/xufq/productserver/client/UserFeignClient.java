package com.xufq.productserver.client;

import com.xufq.productserver.client.config.UserFeignClientConfiguration;
import com.xufq.productserver.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UserFeignClient
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:37 PM
 * @Version 1.0
 */
@FeignClient(name = "user-server", path = "/user-server", fallback = UserFeignClient.UserFeignClientFallback.class, configuration = UserFeignClientConfiguration.class)
public interface UserFeignClient {

    @GetMapping("/user/{userId}")
    UserVo getUserById(@PathVariable String userId);

    @Component
    class UserFeignClientFallback implements UserFeignClient{

        @Override
        public UserVo getUserById(String userId){
            return UserVo.builder()
                    .userId(userId)
                    .userName("FallbackUserName")
                    .build();
        }
    }

}
