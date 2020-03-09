package com.xufq.practicecore.aspect.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ControllerLogger
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/8/2019 09:57 PM
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
@DependsOn("myPointCut")
@ConditionalOnProperty(name = "com.xufq.enable-controller-log", matchIfMissing = false)
public class ControllerLoggerHandler {

    private static ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Before("com.xufq.practicecore.aspect.pointcut.MyPointCut.controllerMethod()")
    public void printLog(JoinPoint joinPoint) {
        log.info("method:{}--params:{}", joinPoint.getSignature().getName(), getParams(joinPoint));
    }

    private String getParams(JoinPoint joinPoint) {
        List<String> params = Arrays.asList(joinPoint.getArgs()).stream().map(param -> {
            try {
                return JSON_MAPPER.writeValueAsString(param);
            } catch (JsonProcessingException ex) {
                return Optional.ofNullable(param).orElse("null").toString();
            }
        }).collect(Collectors.toList());
        return params.stream().collect(Collectors.joining(","));
    }
}
