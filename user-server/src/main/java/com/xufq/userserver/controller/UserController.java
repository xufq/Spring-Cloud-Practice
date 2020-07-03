package com.xufq.userserver.controller;

import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.service.UserService;
import com.xufq.userserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/uuid/{uuid}")
    public UserVo getUserByUuid(@PathVariable String uuid){
        return userService.getUserByUuid(uuid);
    }

    @GetMapping("/account/{accountName}")
    public UserVo getUserByAccountName(@PathVariable String accountName) {
        return userService.getUserByAccountName(accountName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@Valid @RequestBody UserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PostMapping("/private")
    @OnlyAdmin
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUserPrivate(@Valid @RequestBody UserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UserBo userBo) {
        userService.updateUserInfo(userBo);
    }

    @PutMapping("/password")
    public void updatePassword(@Valid @RequestBody PasswordBo passwordBo) {
        userService.updatePassword(passwordBo);
    }

    @DeleteMapping("/{uuid}")
    public void deleteUser(@PathVariable String uuid) {
        userService.deleteUser(uuid);
    }

}
