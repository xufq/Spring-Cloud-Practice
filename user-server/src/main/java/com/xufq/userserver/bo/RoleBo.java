package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleBo {
    private Integer id;

    private String roleCode;

    private String roleName;

    private String deleted;
}
