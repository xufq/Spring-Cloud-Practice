package com.xufq.practicecore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/3/2020 03:37 PM
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -1L;

    private String uuid;
    private String accountName;
    private String userName;
    private String password;
    private List<RoleVo> roles;
    private LocalDateTime expiredDate;
}
