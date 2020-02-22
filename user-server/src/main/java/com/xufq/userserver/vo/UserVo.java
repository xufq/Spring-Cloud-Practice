package com.xufq.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:45 PM
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer id;
    private String accountName;
    private String userName;
    private String password;
    private String deleted;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
