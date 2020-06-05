package com.xufq.loginserver.utils;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@Log
public class RedisUtil {
    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    public <T> T get(String key) {
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            return (T)operations.get(key);
        } catch (Exception ex) {
            log.warning("Redis类型转换失败。key: "+key);
        }
        return null;
    }
}
