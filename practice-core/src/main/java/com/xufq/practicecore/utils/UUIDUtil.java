package com.xufq.practicecore.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtil {
    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
