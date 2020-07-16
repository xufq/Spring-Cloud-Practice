package com.xufq.userserver.controller;

import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.SaveUserBo;
import com.xufq.userserver.bo.UpdateUserBo;
import com.xufq.userserver.service.UserService;
import com.xufq.userserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@Valid @RequestBody SaveUserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PostMapping(value = "/private", produces = MediaType.TEXT_PLAIN_VALUE)
    @OnlyAdmin
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUserPrivate(@Valid @RequestBody SaveUserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UpdateUserBo userBo) {
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
