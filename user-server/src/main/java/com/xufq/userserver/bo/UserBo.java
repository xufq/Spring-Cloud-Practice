package com.xufq.userserver.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserEntity
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:35 PM
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class UserBo {
    @NotNull
    private String accountName;
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
