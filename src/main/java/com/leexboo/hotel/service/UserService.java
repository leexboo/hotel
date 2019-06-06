package com.leexboo.hotel.service;

import com.leexboo.hotel.model.User;

public interface UserService {
    User getUser(String phone, String password);
    int register(User user);

}
