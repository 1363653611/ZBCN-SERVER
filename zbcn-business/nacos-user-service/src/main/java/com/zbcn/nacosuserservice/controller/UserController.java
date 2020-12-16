package com.zbcn.nacosuserservice.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.nacosuserservice.service.UserService;
import com.zbcn.nacosuserservice.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseResult create(@RequestBody User user) {
        userService.create(user);
        return ResponseResult.success("操作成功");
    }

    @GetMapping("/{id}")
    public ResponseResult<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        log.info("根据id获取用户信息，用户名称为：{}",user.getUserName());
        return ResponseResult.success(user);
    }

    @GetMapping("/getUserByIds")
    public ResponseResult<List<User>> getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList= userService.getUserByIds(ids);
        log.info("根据ids获取用户信息，用户列表为：{}",userList);
        return ResponseResult.success(userList);
    }

    @GetMapping("/getByUsername")
    public ResponseResult<User> getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return ResponseResult.success(user);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        userService.update(user);
        return ResponseResult.success("操作成功");
    }

    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseResult.success("操作成功");
    }
}

