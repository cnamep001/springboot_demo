package com.m.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Order {
    private long id;
    private User user;
    private Menu menu;
    private Admin admin;
    private LocalDateTime date;
    private int state;
    private int is_delete;
}
