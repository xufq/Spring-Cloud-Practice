package com.xufq.productserver.service;

import com.xufq.productserver.client.UserFeignClient;
import com.xufq.productserver.vo.ProductVo;
import com.xufq.productserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:19 PM
 * @Version 1.0
 */
@Service
public class ProductService {

    @Autowired
    private UserFeignClient userFeignClient;

    public ProductVo getProductById(String productId) {
        String userId = UUID.randomUUID().toString();
        UserVo user = userFeignClient.getUserById(userId);
        return ProductVo.builder()
                .productId(productId)
                .productCode(UUID.randomUUID().toString())
                .productName("苹果")
                .userName(user.getUserName())
                .build();
    }
}
