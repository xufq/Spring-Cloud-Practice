package com.xufq.userserver.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleVo {
    private String roleCode;

    private String roleName;

    private String deleted;
}
