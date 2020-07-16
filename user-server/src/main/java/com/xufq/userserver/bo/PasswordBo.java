package com.xufq.userserver.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName PasswordBo
 * @Description update password info
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:35 PM
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class PasswordBo {
    @NotNull
    private String uuid;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmPassword;
    @NotNull
    private Integer version;
}
