package com.zbcn.seataorderservice.service;

import com.zbcn.seataorderservice.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
