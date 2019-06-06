package com.leexboo.hotel.service.impl;

import com.leexboo.hotel.mapper.UserMapper;
import com.leexboo.hotel.model.User;
import com.leexboo.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(String phone,String password){
        return userMapper.getUser(phone,password);
    }

    public int register(User user){
        return userMapper.register(user);
    }

}
