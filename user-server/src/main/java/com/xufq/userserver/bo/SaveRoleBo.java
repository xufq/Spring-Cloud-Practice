package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class SaveRoleBo {
    @NotNull
    private String roleCode;

    @NotNull
    private String roleName;
}
