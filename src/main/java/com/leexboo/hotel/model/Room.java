package com.leexboo.hotel.model;

import lombok.Data;

/**
 * 房间
 */
@Data
public class Room {
    private int roomId;       //房间id
    private String location;  //位置
    private int price;        //价格
    private String roomType;  //房间类型
    private int state;        //状态 0-未入住，1-已入住
}
