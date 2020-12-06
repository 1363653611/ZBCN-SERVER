package com.zbcn.demo.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zbcn.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    public void create(User user) {
       log.info("创建用户：{}", JSON.toJSONString(user));
    }

    public User getUser(Long id) {
        User user = new User();
        user.setId(1L);
        user.setUserName("张珊");
        return user;
    }

    public List<User> getUserByIds(List<Long> ids) {
        User user = new User();
        user.setId(1L);
        user.setUserName("张珊");
        return Lists.newArrayList(user);
    }

    public User getByUsername(String username) {
        User user = new User();
        user.setId(1L);
        user.setUserName("张珊");
        return user;
    }

    public void update(User user) {
        log.info("更新用户：{}", JSON.toJSONString(user));
    }

    public void delete(Long id) {
        log.info("删除用户：{}", id);
    }
}
