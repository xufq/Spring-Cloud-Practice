package com.xufq.userserver.controller;

import com.xufq.userserver.bo.LoginBo;
import com.xufq.userserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:13 PM
 * @Version 1.0
 */
@RestController("/login")
public class LoginController {

    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void login(@RequestBody LoginBo loginBo){
        service.validateUser(loginBo);
    }
}
