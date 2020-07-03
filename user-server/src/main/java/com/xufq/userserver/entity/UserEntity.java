package com.xufq.userserver.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName UserEntity
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:35 PM
 * @Version 1.0
 */
@Data
@Builder
public class UserEntity {
    private Integer id;
    private String uuid;
    private String accountName;
    private String userName;
    private String password;
    private String deleted;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Integer version;
}
