package com.xufq.userserver.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserBo
 * @Description create user
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:35 PM
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class UserBo {
    @NotNull
    private String uuid;
    @NotNull
    private String accountName;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String roleCode;
    @NotNull
    private Integer version;
}
