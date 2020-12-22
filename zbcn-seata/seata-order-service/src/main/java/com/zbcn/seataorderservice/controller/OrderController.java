package com.zbcn.seataorderservice.controller;

import com.zbcn.common.response.ResponseResult;
import com.zbcn.seataorderservice.domain.Order;
import com.zbcn.seataorderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public ResponseResult create(Order order) {
        orderService.create(order);
        return ResponseResult.success("订单创建成功!");
    }
}
