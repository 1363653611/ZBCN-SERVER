package com.zbcn.feignserver.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.feignserver.api.UserService;
import com.zbcn.feignserver.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseResult<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public ResponseResult getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public ResponseResult create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
