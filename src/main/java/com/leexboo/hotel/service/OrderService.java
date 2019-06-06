package com.leexboo.hotel.service;

import com.leexboo.hotel.model.Order;

public interface OrderService {
    int addOrder(Order order);
    int deleteOrder(int orderId,int roomId);
}
