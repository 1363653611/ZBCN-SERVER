package com.zbcn.seataorderservice.service;

import com.zbcn.common.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    ResponseResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
