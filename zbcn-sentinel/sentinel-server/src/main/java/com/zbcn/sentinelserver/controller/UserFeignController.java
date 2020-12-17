package com.zbcn.sentinelserver.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.sentinelserver.servcie.UserService;
import com.zbcn.sentinelserver.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userFallbackService;

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id) {
        return userFallbackService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public ResponseResult getByUsername(@RequestParam String username) {
        return userFallbackService.getByUsername(username);
    }

    @PostMapping("/create")
    public ResponseResult create(@RequestBody User user) {
        return userFallbackService.create(user);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        return userFallbackService.update(user);
    }

    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        return userFallbackService.delete(id);
    }
}
