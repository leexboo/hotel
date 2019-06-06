package com.leexboo.hotel.mapper;

import com.leexboo.hotel.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int addOrder(Order order);
    int deleteOrder(int orderId);
}
