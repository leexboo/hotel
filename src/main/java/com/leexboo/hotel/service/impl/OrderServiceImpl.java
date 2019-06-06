package com.leexboo.hotel.service.impl;

import com.leexboo.hotel.mapper.OrderMapper;
import com.leexboo.hotel.mapper.RoomMapper;
import com.leexboo.hotel.model.Order;
import com.leexboo.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Transactional(propagation=Propagation.REQUIRED)
    public int addOrder(Order order){
        roomMapper.updateState(order.getRoomId(),1);
        return orderMapper.addOrder(order);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteOrder(int orderId,int roomId){
        roomMapper.updateState(roomId,0);
        return orderMapper.deleteOrder(orderId);
    }
}
