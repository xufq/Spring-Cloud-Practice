package com.xufq.practicecore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RoleVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 3/3/2020 03:44 PM
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleVo implements Serializable {
    private static final long serialVersionUID = -1L;
    private String roleCode;
    private String roleName;
}
