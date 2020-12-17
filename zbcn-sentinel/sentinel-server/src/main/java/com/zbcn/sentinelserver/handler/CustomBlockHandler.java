package com.zbcn.sentinelserver.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zbcn.common.response.ResponseResult;

public class CustomBlockHandler {
    public static ResponseResult  handleException(BlockException exception){
        return ResponseResult.success("自定义限流信息");
    }
}
