package com.zbcn.feignserver.api;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.feignserver.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-service", fallback = UserFallbackService.class)
public interface UserService {

    @PostMapping("/user/create")
    ResponseResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    ResponseResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    ResponseResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    ResponseResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    ResponseResult delete(@PathVariable Long id);
}

