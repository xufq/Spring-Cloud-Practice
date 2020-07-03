package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleBo {
    private String uuid;

    private String roleCode;

    private String roleName;

    private String deleted;
}
