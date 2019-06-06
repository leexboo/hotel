package com.leexboo.hotel.model;

import lombok.Data;

/**
 * 订单
 */
@Data
public class Order {
    private int orderId;      //订单编号
    private int userId;       //用户编号
    private int roomId;       //房间编号
    private String startTime; //开始时间
    private String endTime;   //结束时间
}
