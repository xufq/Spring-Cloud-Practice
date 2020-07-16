package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressDeleteBo {

    @NotNull
    private String addressUuid;

    @NotNull
    private Integer version;
}