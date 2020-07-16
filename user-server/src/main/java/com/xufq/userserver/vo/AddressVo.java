package com.xufq.userserver.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AddressVo {

    private String addressUuid;

    private String userUuid;

    private String address;

    private Long phone;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    private String deleted;

    private Integer version;
}