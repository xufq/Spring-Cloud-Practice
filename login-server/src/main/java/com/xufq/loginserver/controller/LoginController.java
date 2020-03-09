package com.xufq.loginserver.controller;

import com.xufq.loginserver.bo.LoginBo;
import com.xufq.loginserver.service.LoginService;
import com.xufq.loginserver.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public void login(@RequestBody LoginBo loginBo) {
        service.login(loginBo);
    }

    @GetMapping("/validate")
    public UserVo validate(){
        return service.validateUser();
    }
}
