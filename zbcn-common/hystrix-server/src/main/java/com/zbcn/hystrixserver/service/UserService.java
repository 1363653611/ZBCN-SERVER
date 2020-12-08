package com.zbcn.hystrixserver.service;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.zbcn.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
@Slf4j
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public ResponseResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    public ResponseResult getDefaultUser(@PathVariable Long id) {
        log.info("获取默认user：" + id);
        return commonResult();
    }

    private ResponseResult commonResult() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("id", -1L);
        result.put("userName", "defaultUser");
        result.put("password", "123456");
        return ResponseResult.success(result);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser",
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool")
    public ResponseResult getUserCommand(Long id) {
        log.info("调用getUserCommand：" + id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser2", ignoreExceptions = {NullPointerException.class})
    public ResponseResult getUserException(Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    public ResponseResult getDefaultUser2(@PathVariable Long id, Throwable e) {
        log.error("getDefaultUser2 id:{},throwable class:{}", id, e.getClass());
        return commonResult();
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public ResponseResult getUserCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
    }

    /**
     * 为缓存生成key的方法
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public ResponseResult removeCache(Long id) {
        log.info("removeCache id:{}", id);
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, ResponseResult.class, id);
    }

    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<Map> getUserFuture(Long id) {
        return new AsyncResult<Map>(){
            @Override
            public Map invoke() {
                ResponseResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/{1}", ResponseResult.class, id);
                Map data = (Map) commonResult.getData();
                log.info("getUserById username:{}", data.get("userName"));
                return data;
            }
        };
    }

    @HystrixCommand
    public List<Map> getUserByIds(List<Long> ids) {
        log.info("getUserByIds:{}", ids);
        ResponseResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/getUserByIds?ids={1}", ResponseResult.class, StringUtils.join(ids, ","));
        return (List<Map>) commonResult.getData();
    }

}
