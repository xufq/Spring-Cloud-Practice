package com.xufq.loginserver.controller;

import com.xufq.loginserver.bo.LoginBo;
import com.xufq.loginserver.service.LoginService;
import com.xufq.loginserver.utils.RedisUtil;
import com.xufq.loginserver.vo.ImageCodeVo;
import com.xufq.loginserver.vo.UserVo;
import feign.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/7/2020 07:13 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;
    private final RedisUtil redisUtil;

    public LoginController(LoginService service,
                           RedisUtil redisUtil) {
        this.service = service;
        this.redisUtil = redisUtil;
    }

    @GetMapping(value = "/validate")
    public UserVo validate(@RequestHeader("token") String token){
        return service.validateUser(token);
    }

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginBo loginBo) {
        String authenticationId = request.getHeader("AuthenticationId");
        ImageCodeVo imageCodeVo = redisUtil.get("CaptchaKey:"+authenticationId);
        String token = service.login(imageCodeVo,loginBo);
        response.setHeader("Authentication", token);
    }
}
