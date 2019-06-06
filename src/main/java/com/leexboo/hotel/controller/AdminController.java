package com.leexboo.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.leexboo.hotel.model.Admin;
import com.leexboo.hotel.model.ResponseTemplate;
import com.leexboo.hotel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "adminLogin")
    public ResponseTemplate adminLogin(Admin admin){
        JSONObject result = new JSONObject();
        if(adminService.getAdmin(admin)!=null){
            result.put("status",1);
            result.put("data",adminService.getAdmin(admin));
        }else{
            result.put("status",0);
        }
        return ResponseTemplate.builder()
                .code(200)
                .message("OK")
                .data(result)
                .build();
    }
}
