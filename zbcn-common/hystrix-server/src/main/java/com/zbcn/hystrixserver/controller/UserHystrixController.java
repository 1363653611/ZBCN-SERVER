package com.zbcn.hystrixserver.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.hystrixserver.service.UserService;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/user")
public class UserHystrixController {
    @Autowired
    private UserService userService;

    @GetMapping("/testFallback/{id}")
    public ResponseResult testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/testCommand/{id}")
    public ResponseResult testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    @GetMapping("/testException/{id}")
    public ResponseResult testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    @GetMapping("/testCache/{id}")
    public ResponseResult testCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return ResponseResult.success("操作成功");
    }

    @GetMapping("/testRemoveCache/{id}")
    public ResponseResult testRemoveCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        return ResponseResult.success("操作成功");
    }

    @GetMapping("/testCollapser")
    public ResponseResult testCollapser() throws ExecutionException, InterruptedException {
        Future<Map> future1 = userService.getUserFuture(1L);
        Future<Map> future2 = userService.getUserFuture(2L);
        future1.get();
        future2.get();
        Thread.sleep(200);
        Future<Map> future3 = userService.getUserFuture(3L);
        future3.get();
        return ResponseResult.success("操作成功");
    }


}
