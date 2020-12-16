package com.zbcn.nacosribbonservice.controller;

import com.zbcn.common.response.ApiStatus;
import com.zbcn.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.consul-user-service}")
    private String userServiceUrl;

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    @GetMapping("/getByUsername")
    public ResponseResult getByUsername(@RequestParam String username) {
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, username);
    }

    @GetMapping("/getEntityByUsername")
    public ResponseResult getEntityByUsername(@RequestParam String username) {
        ResponseEntity<ResponseResult> entity = restTemplate.getForEntity(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, username);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return ResponseResult.fail(ApiStatus.INTERNAL_SERVER_ERROR.getCode(),ApiStatus.INTERNAL_SERVER_ERROR.getValue(),"操作失败");
        }
    }

    @PostMapping("/create")
    public ResponseResult create(@RequestBody Map user) {
        return restTemplate.postForObject(userServiceUrl + "/user/create", user, ResponseResult.class);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody Map user) {
        return restTemplate.postForObject(userServiceUrl + "/user/update", user, ResponseResult.class);
    }

    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, ResponseResult.class, id);
    }

}
