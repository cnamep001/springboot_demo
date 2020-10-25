package com.m.dao;

import com.m.entity.Admin;

public interface AdminDao {
    public Admin login(String username, String password);
}
