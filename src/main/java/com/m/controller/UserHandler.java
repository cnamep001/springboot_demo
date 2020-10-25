package com.m.controller;


import com.m.dao.OrderDao;
import com.m.dao.UserDao;
import com.m.entity.Order;
import com.m.entity.User;
import com.m.entity.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserDao userRepository;

    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        List<User> list = userRepository.findAll((page-1)*limit,limit);
        userVO.setCount(list.size());
        userVO.setData(list);
        return userVO;
    }

    @RequestMapping("/save")
    //Post请求
    public String save(User user){
        user.setRegisterdate(LocalDateTime.now());
        user.setIs_delete(1);
        userRepository.save(user);
        return "user_manage";
    }

    @RequestMapping("/update")
    //Post请求
    public String update(User user){
        userRepository.update(user);
        //1、退出用户
        //2、返回登陆页面,重新登陆
        return "redirect:/account/logout";
    }

    /**
     *
     *
     * 如果要删这个用户，那么跟这个用户相关的订单也有删除
     *      order
     */

    @RequestMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        //先删用户订单，再删用户
        List<Order> orders = userRepository.findOrderById(id);
        if(orders.isEmpty()) {
            orderDao.deleteByUid(id);
            userRepository.deleteById(id);
            return "user_manage";
        }
        return "user_manage";
    }
}