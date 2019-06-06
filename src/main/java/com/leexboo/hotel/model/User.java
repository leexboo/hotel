package com.leexboo.hotel.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 用户
 */
@Data
public class User implements Serializable {
    private int userId;       //用户id
    private String userName;  //用户姓名
    private String password;  //密码
    private String idCard;    //身份证
    private String phone;     //电话号码
}

