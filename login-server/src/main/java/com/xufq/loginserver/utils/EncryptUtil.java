package com.xufq.loginserver.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName EncryptUtil
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/8/2020 01:14 PM
 * @Version 1.0
 */
@UtilityClass
public class EncryptUtil {

    public String encryptSHA(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(str.getBytes());
            return new BigInteger(sha.digest()).toString();
        } catch(NoSuchAlgorithmException ex){
            return str;
        }
    }
}
