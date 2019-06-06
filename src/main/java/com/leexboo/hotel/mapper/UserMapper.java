package com.leexboo.hotel.mapper;

import com.leexboo.hotel.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //查询用户信息
    User getUser(@Param("phone") String phone,@Param("password") String password);
    //用户注册
    int register(User user);
}
