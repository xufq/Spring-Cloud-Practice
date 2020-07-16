package com.xufq.loginserver.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtil {
    public String getUUID(){
        return UUID.randomUUID().toString();
    }
}
