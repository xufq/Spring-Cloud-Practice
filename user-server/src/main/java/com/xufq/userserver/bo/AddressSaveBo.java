package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressSaveBo {

    @NotNull
    private String userUuid;

    @NotNull
    private String address;

    @NotNull
    private Long phone;
}
