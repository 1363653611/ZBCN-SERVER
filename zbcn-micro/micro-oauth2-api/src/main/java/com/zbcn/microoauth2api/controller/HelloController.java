package com.zbcn.microoauth2api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *  测试接口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/23 19:21
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }
}
