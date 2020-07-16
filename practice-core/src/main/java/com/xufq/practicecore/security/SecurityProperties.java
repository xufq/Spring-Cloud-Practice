package com.xufq.practicecore.security;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SecurityProperties
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/29/2020 08:36 PM
 * @Version 1.0
 */
@Component
@ConditionalOnProperty("spring.security.user.name")
@ConfigurationProperties(prefix = "spring.security.user")
@Data
public class SecurityProperties {

    private String name;

    private String password;

    private List<String> roles = Arrays.asList();
}
