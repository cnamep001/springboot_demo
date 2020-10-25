package com.m.dao;

import com.m.entity.Menu;
import com.m.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    public List<Menu> findAll(int index, int limit);
    public int count();
    public void save(Menu menu);
    public void save1(Menu menu);
    public Menu findById(long id);
    public void update(Menu menu);
    public void deleteById(long id);
    public List<Order> findOrderById(long id);
}
