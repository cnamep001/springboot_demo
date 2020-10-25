package com.m.dao;

import com.m.entity.Order;
import com.m.entity.User;

import java.util.List;

public interface UserDao {
    public User login(String username, String password);

    public List<User> findAll(int index, int limit);

    public void save(User user);
    public void update(User user);

    public void deleteById(long id);
    public Order findOrderById(long uid);
}
