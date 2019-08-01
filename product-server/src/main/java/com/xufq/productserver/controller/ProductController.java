package com.xufq.productserver.controller;

import com.xufq.productserver.service.ProductService;
import com.xufq.productserver.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 09:19 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ProductVo getProductById(@PathVariable String productId){
        return productService.getProductById(productId);
    }
}
