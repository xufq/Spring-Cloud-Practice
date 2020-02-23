package com.xufq.userserver.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName ImageCodeVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 12:54 PM
 * @Version 1.0
 */
@Data
@Builder
public class ImageCodeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String imageCode;

    private LocalDateTime expiredDate;
}
