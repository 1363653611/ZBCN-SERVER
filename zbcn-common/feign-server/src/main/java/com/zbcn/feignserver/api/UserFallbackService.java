package com.zbcn.feignserver.api;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.feignserver.vo.User;
import org.springframework.stereotype.Component;
/**
 *  feign 服务申明式调用服务降级功能
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/8 15:11
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public ResponseResult create(User user) {
        return null;
    }

    @Override
    public ResponseResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser");
        return ResponseResult.success(defaultUser);
}

    @Override
    public ResponseResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser");
        return ResponseResult.success(defaultUser);
    }

    @Override
    public ResponseResult update(User user) {
        return ResponseResult.fail("服务调用失败。。。");
    }

    @Override
    public ResponseResult delete(Long id) {
        return  ResponseResult.fail("服务调用失败。。。");
    }
}
