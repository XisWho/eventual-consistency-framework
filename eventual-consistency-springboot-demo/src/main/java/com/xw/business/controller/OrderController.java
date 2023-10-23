package com.xw.business.controller;

import com.xw.business.dto.OrderDTO;
import com.xw.business.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private RedisClient redisClient;

    @GetMapping("/test1")
    public String test1() {
        // 构造数据
        OrderDTO orderInfoDTO = new OrderDTO();
        orderInfoDTO.setId("1");
        orderInfoDTO.setDesc("测试商品");
        // 插入异构存储
        redisClient.store(orderInfoDTO);
        return "ok";
    }

    @GetMapping("/test2")
    public String test2() {
        // 插入异构存储
        redisClient.store();
        return "ok";
    }

    @GetMapping("/test3")
    public String test3() {
        // 构造数据
        OrderDTO orderInfoDTO = new OrderDTO();
        orderInfoDTO.setId("1");
        orderInfoDTO.setDesc("测试商品");
        // 插入异构存储
        redisClient.store(orderInfoDTO, 1);
        return "ok";
    }

    @GetMapping("/test4")
    public String test4() {
        // 构造数据
        OrderDTO orderInfoDTO = new OrderDTO();
        orderInfoDTO.setId("1");
        orderInfoDTO.setDesc("测试商品");
        // 插入异构存储
        redisClient.store(orderInfoDTO, Integer.valueOf(1));
        return "ok";
    }

}
