package com.xufq.userserver.bo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class AddressUpdateBo {

    @NotNull
    private String addressUuid;

    @NotNull
    private String address;

    @NotNull
    private Long phone;

    @NotNull
    private Integer version;
}