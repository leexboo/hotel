package com.leexboo.hotel.mapper;

import com.leexboo.hotel.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoomMapper {
    int addRoom(Room room);
    Room getRoom();
    int deleteRoom(int roomId);
    int updateState(@Param("roomId") int roomId,@Param("state") int state);
}
