package com.xufq.practicecore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class AspectUtil {

    private static ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String getParams(JoinPoint joinPoint) {
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
