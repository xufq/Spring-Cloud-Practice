package com.xufq.practicecore.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xufq.practicecore.constants.DateForamtPattern;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName LocalDateTimeSerializer
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/7/2020 02:57 PM
 * @Version 1.0
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(localDateTime.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(DateForamtPattern.YYYY_MM_DD_HH_MM_SS)));
    }
}
