package com.leexboo.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.leexboo.hotel.mapper.RoomMapper;
import com.leexboo.hotel.model.ResponseTemplate;
import com.leexboo.hotel.model.Room;
import com.leexboo.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping(value = "addRoom")
    public ResponseTemplate addRoom(Room room){
        JSONObject result = new JSONObject();
        result.put("status",roomService.addRoom(room));
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }

    @PostMapping(value = "getRoom")
    public ResponseTemplate getRoom(){
        JSONObject result = new JSONObject();
        result.put("data",roomService.getRoom());
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }

    @PostMapping(value = "deleteRoom")
    public ResponseTemplate deleteRoom(int roomId){
        JSONObject result = new JSONObject();
        result.put("status",roomService.deleteRoom(roomId));
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }
}
