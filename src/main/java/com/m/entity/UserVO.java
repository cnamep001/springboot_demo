package com.m.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private int code;
    private String msg;
    private int count;
    private List<User> data;


}
