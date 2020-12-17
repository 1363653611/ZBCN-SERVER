package com.zbcn.sentinelserver.servcie;

import com.zbcn.common.response.ApiStatus;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.sentinelserver.vo.User;
import org.springframework.stereotype.Service;
/**
 *  服务降级逻辑
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/17 14:13
 */
@Service
public class UserFallbackService implements UserService {
    @Override
    public ResponseResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        ResponseResult<User> success = ResponseResult.success(defaultUser);
        success.setMsg("服务降级返回");
        return success;
    }

    @Override
    public ResponseResult getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        ResponseResult<User> success = ResponseResult.success(defaultUser);
        success.setMsg("服务降级返回");
        return success;
    }

    @Override
    public ResponseResult getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        ResponseResult<User> success = ResponseResult.success(defaultUser);
        success.setMsg("服务降级返回");
        return success;
    }

    @Override
    public ResponseResult update(User user) {
        return ResponseResult.fail(ApiStatus.SERVICE_UNAVAILABLE,"调用失败，服务被降级");
    }

    @Override
    public ResponseResult delete(Long id) {
        return ResponseResult.fail(ApiStatus.SERVICE_UNAVAILABLE,"调用失败，服务被降级");
    }

}
