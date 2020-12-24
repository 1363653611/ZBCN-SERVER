package com.zbcn.microoauth2api.controller;

import com.zbcn.microoauth2api.domain.UserDTO;
import com.zbcn.microoauth2api.holder.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  获取登录用户信息接口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/23 19:32
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}
