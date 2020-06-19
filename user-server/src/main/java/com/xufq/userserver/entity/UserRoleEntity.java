package com.xufq.userserver.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRoleEntity {

    private Integer id;

    private Integer userId;

    private Integer roleId;

    private String deleted;

}
