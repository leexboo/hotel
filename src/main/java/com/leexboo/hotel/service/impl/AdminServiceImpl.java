package com.leexboo.hotel.service.impl;

import com.leexboo.hotel.mapper.AdminMapper;
import com.leexboo.hotel.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdmin(Admin admin){
        return adminMapper.getAdmin(admin);
    }
}
