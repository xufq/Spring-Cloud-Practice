package com.xufq.userserver.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRoleEntity {

    private Integer id;

    private String userUuid;

    private String roleUuid;

    private String deleted;

}
