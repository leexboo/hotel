package com.leexboo.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.leexboo.hotel.model.Order;
import com.leexboo.hotel.model.ResponseTemplate;
import com.leexboo.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "addOrder")
    public ResponseTemplate addOrder(Order order){
        JSONObject result = new JSONObject();
        result.put("status",orderService.addOrder(order));
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }

    @PostMapping(value = "deleteOrder")
    public ResponseTemplate deleteOrder(int orderId,int roomId){
        JSONObject result = new JSONObject();
        result.put("status",orderService.deleteOrder(orderId,roomId));
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }
}
