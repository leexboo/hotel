package com.leexboo.hotel.mapper;

import com.leexboo.hotel.model.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin getAdmin(Admin admin);
}
