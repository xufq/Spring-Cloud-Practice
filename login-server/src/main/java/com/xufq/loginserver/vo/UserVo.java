package com.xufq.loginserver.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/3/2020 03:37 PM
 * @Version 1.0
 */
@Data
public class UserVo {
    private String accountName;
    private String userName;
    private String password;
    private List<RoleVo> roles;
    private LocalDateTime expiredDate;
}
