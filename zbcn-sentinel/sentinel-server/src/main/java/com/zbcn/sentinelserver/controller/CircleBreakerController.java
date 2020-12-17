package com.zbcn.sentinelserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.sentinelserver.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *  熔断功能
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/17 13:35
 */
@RestController
@RequestMapping("/breaker")
public class CircleBreakerController {

    private static Logger log = LoggerFactory.getLogger(CircleBreakerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @RequestMapping("/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handleFallback")
    public ResponseResult fallback(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    @RequestMapping("/fallbackException/{id}")
    @SentinelResource(value = "fallbackException",fallback = "handleFallback2", exceptionsToIgnore = {NullPointerException.class})
    public ResponseResult fallbackException(@PathVariable Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    public ResponseResult handleFallback(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        ResponseResult<User> success = ResponseResult.success(defaultUser);
        success.setMsg("服务降级返回");
        return ResponseResult.success(success);
    }

    public ResponseResult handleFallback2(@PathVariable Long id, Throwable e) {
        log.error("handleFallback2 id:{},throwable class:{}", id, e.getClass());
        User defaultUser = new User(-2L, "defaultUser2", "123456");
        ResponseResult<User> success = ResponseResult.success(defaultUser);
        success.setMsg( "服务降级返回");
        return success;

    }
}
