package com.m.controller;

import com.m.dao.OrderDao;
import com.m.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderDao orderRepository;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") long mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        Menu menu = new Menu();
        menu.setId(mid);
        order.setUser(user);
        order.setMenu(menu);
        order.setDate(LocalDateTime.now());
        orderRepository.save(order);
        return "user_main";
    }

    @RequestMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User)session.getAttribute("user");
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(user.getId()));
        orderVO.setData(orderRepository.findAllByUid(user.getId(),(page-1)*limit,limit));
        return orderVO;
    }

    @RequestMapping("/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable("mid") long mid){
        orderRepository.deleteByMid(mid);
    }

    @RequestMapping("/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable("uid") long uid){
        orderRepository.deleteByUid(uid);
    }

    @RequestMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByState(0));
        orderVO.setData(orderRepository.findAllByState(0,(page-1)*limit,limit));
        return orderVO;
    }

    @RequestMapping("/updateState/{id}/{state}")
    public String updateState(@PathVariable("id") long id, @PathVariable("state") int state,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        orderRepository.updateState(id,admin.getId(),state);
        return "order_handler";
    }

}

