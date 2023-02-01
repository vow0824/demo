package com.vow.demo.domain.main.user.controller;

import com.vow.demo.domain.main.user.entity.User;
import com.vow.demo.domain.main.user.service.IUserService;
import com.vow.demo.domain.param.UserSaveParam;
import com.vow.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vow
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/info")
    public Result<User> info(@RequestParam Long userId) {
        return Result.success(userService.getById(userId));
    }

    @PostMapping("/save")
    public Result<Long> save(@RequestBody UserSaveParam param) {
        return Result.success(userService.saveUser(param));
    }

    @GetMapping("/delete")
    public Result<Void> delete(@RequestParam Long userId) {
        userService.removeById(userId);
        return Result.success();
    }
}
