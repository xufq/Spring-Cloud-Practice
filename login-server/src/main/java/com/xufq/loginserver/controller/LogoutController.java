package com.xufq.loginserver.controller;

import com.xufq.loginserver.service.LogoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutController {

    private final LogoutService service;

    public LogoutController(LogoutService service) {
        this.service = service;
    }

    @PostMapping
    public void logout(@RequestHeader("token") String token){
        service.logout(token);
    }
}
