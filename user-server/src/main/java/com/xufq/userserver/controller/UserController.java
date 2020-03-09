package com.xufq.userserver.controller;

import com.google.common.collect.Maps;
import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.service.UserService;
import com.xufq.userserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 8/1/2019 10:13 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserVo getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @OnlyAdmin
    public int saveUser(@RequestBody UserBo userBo) {
        return userService.saveUser(userBo);
    }
}
