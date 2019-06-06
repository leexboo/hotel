package com.leexboo.hotel.service.impl;

import com.leexboo.hotel.mapper.RoomMapper;
import com.leexboo.hotel.model.Room;
import com.leexboo.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    public int addRoom(Room room){
        return roomMapper.addRoom(room);
    }

    public Room getRoom(){
        return roomMapper.getRoom();
    }

    public int deleteRoom(int roomId){
        return roomMapper.deleteRoom(roomId);
    }


}
