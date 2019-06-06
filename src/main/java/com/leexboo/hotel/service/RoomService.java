package com.leexboo.hotel.service;

import com.leexboo.hotel.model.Room;

public interface RoomService {
    int addRoom(Room room);
    Room getRoom();
    int deleteRoom(int roomId);
}
