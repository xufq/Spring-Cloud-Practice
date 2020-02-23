package com.xufq.practicecore.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration;

/**
 * @ClassName SessionConfiguration
 * @Description spring session configuration
 * @Author fangqiang.xu
 * @Date 2/9/2020 03:32 PM
 * @Version 1.0
 */
@Configuration
public class SessionConfiguration {

    @Bean("jdbcHttpSessionConfiguration")
    public JdbcHttpSessionConfiguration configJdbcHttpSessionConfiguration(){
        return new JdbcHttpSessionConfiguration();
    }
}
