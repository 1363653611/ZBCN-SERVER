package com.zbcn.sentinelserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.sentinelserver.handler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  限流
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/17 10:38
 */
@RestController
@RequestMapping("/rateLimit")
public class RateLimitController {

    /**
     * 按资源名称限流，需要指定限流处理逻辑
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public ResponseResult byResource() {
        return ResponseResult.success("按资源名称限流");
    }

    /**
     * 按URL限流，有默认的限流处理逻辑
     */
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handleException")
    public ResponseResult byUrl() {
        return ResponseResult.success("按url限流");
    }

    public ResponseResult handleException(BlockException exception){
        return ResponseResult.success(exception.getClass().getCanonicalName());
    }

    /**
     * 自定义通用的限流处理逻辑
     */
    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandler = "handleException",blockHandlerClass = CustomBlockHandler.class)
    public ResponseResult customBlockHandler() {
        return ResponseResult.success("限流成功");
    }

}
