package com.leexboo.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.leexboo.hotel.annotation.AuthToken;
import com.leexboo.hotel.mapper.UserMapper;
import com.leexboo.hotel.model.Order;
import com.leexboo.hotel.model.ResponseTemplate;
import com.leexboo.hotel.model.User;
import com.leexboo.hotel.service.UserService;
import com.leexboo.hotel.util.ConstantKit;
import com.leexboo.hotel.util.Md5TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@RestController
public class UserController {

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public ResponseTemplate login(String phone, String password) {

        User user = userService.getUser(phone,password);

        JSONObject result = new JSONObject();

        if (user != null) {
            Jedis jedis = new Jedis("localhost", 6379);
            String token = tokenGenerator.generate(phone, password);
            //设置token以及过期时间
            jedis.set(token, phone);
            //设置key生存时间，当key过期时，它会被自动删除，时间是秒
            jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
            //记录设置token的时间，以便于后续调用时分析token是否过期
            Long currentTime = System.currentTimeMillis();
            jedis.set(token + phone, currentTime.toString());
            jedis.expire(token+phone,ConstantKit.TOKEN_EXPIRE_TIME);
            //用完关闭
            jedis.close();
            result.put("token", token);
            result.put("status",1);
        } else {
            result.put("status",0);
        }
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }


    @PostMapping(value = "test")
    @AuthToken
    public ResponseTemplate test() {
        return ResponseTemplate.builder()
                .code(200)
                .message("Success")
                .data("test url")
                .build();
    }

    @PostMapping(value = "register")
    public ResponseTemplate register(@RequestBody User user){
        JSONObject result = new JSONObject();
        result.put("status",userService.register(user));
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }

}

