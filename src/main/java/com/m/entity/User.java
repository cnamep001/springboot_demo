package com.m.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User{
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private String telephone;
    private LocalDateTime registerdate;
    private String address;
    private int is_delete;

}
