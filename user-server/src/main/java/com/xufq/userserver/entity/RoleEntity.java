package com.xufq.userserver.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RoleEntity {

    private Integer id;

    private String uuid;

    private String roleCode;

    private String roleName;

    private String deleted;

}
