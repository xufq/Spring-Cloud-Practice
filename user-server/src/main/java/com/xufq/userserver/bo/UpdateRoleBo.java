package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class UpdateRoleBo {
    @NotNull
    private String uuid;

    @NotNull
    private String roleCode;

    @NotNull
    private String roleName;

    @NotNull
    private Integer version;
}
