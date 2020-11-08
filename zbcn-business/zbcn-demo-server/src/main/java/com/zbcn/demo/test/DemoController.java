package com.zbcn.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-demo-url")
public class DemoController {

    @GetMapping("/index")
    public String index(){
        return "welcome to zbcn-cloud-server";
    }
}
