package com.xufq.productserver.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName ProductVo
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:20 PM
 * @Version 1.0
 */
@Data
@Builder
public class ProductVo {

    private String productId;
    private String productCode;
    private String productName;
    private String userName;
}
