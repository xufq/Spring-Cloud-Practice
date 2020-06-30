package com.xufq.userserver.controller;

import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.PasswordBo;
import com.xufq.userserver.bo.UserBo;
import com.xufq.userserver.service.UserService;
import com.xufq.userserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{accountName}")
    public UserVo getUserById(@PathVariable String accountName) {
        return userService.getUserByAccountName(accountName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int saveUser(@RequestBody UserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PostMapping("/private")
    @OnlyAdmin
    @ResponseStatus(HttpStatus.CREATED)
    public int saveUserPrivate(@RequestBody UserBo userBo) {
        return userService.saveUser(userBo);
    }

    @PutMapping
    public void updateUser(@RequestBody UserBo userBo) {
        userService.updateUserInfo(userBo);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody PasswordBo passwordBo) {
        userService.updatePassword(passwordBo);
    }

    @DeleteMapping("/{accountName}")
    public void deleteUser(@PathVariable("accountName") String accountName) {
        userService.deleteUser(accountName);
    }

}
