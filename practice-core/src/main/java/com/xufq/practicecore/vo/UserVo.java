package com.xufq.practicecore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserVo {
    private String accountName;
    private String userName;
    private List<RoleVo> roles;
    private LocalDateTime expiredDate;
}
