package com.xufq.userserver.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:45 PM
 * @Version 1.0
 */
@Data
@Builder
public class UserVo {
    private String userId;
    private String userName;
}
