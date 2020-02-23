package com.xufq.userserver.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName LoginBo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 08:22 PM
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginBo {
    @NotNull
    private String accountName;
    @NotNull
    private String password;
    @NotNull
    private String captchaText;
}
