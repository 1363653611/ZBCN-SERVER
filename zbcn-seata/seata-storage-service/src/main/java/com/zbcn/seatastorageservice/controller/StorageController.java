package com.zbcn.seatastorageservice.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.seatastorageservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/decrease")
    public ResponseResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return ResponseResult.success("扣减库存成功！");
    }
}
