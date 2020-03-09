package com.xufq.practicecore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xufq.practicecore.constants.DateForamtPattern;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName ObjectMapperUtil
 * @Description Json format util
 * @Author fangqiang.xu
 * @Date 3/3/2020 02:13 PM
 * @Version 1.0
 */
@UtilityClass
public class ObjectMapperUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateForamtPattern.YYYY_MM_DD_HH_MM_SS)));
        OBJECT_MAPPER.registerModule(timeModule);
    }

    public ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }
}
