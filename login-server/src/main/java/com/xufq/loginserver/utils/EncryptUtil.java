package com.xufq.loginserver.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName EncryptUtil
 * @Description Encrypt util
 * @Author fangqiang.xu
 * @Date 2/8/2020 01:14 PM
 * @Version 1.0
 */
@UtilityClass
public class EncryptUtil {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String encode(String str) {
        return EncryptUtil.bCryptPasswordEncoder.encode(str);
    }

    public boolean match(String rawStr, String encodeStr){
        return EncryptUtil.bCryptPasswordEncoder.matches(rawStr, encodeStr);
    }
}
