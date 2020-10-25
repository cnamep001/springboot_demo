package com.m.controller;


import com.m.dao.MenuDao;
import com.m.dao.TypeDao;
import com.m.entity.Menu;
import com.m.entity.MenuVO;

import com.m.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private TypeDao typeDao;

    @RequestMapping("/findAllMenu")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        MenuVO menuVO = new MenuVO();
        menuVO.setCode(0);
        menuVO.setMsg("");
        menuVO.setCount(menuDao.count());
        menuVO.setData(menuDao.findAll((page-1)*limit,limit));
        return menuVO;
    }

    @RequestMapping("/findAllType")
    public String findAll(Model model){
        model.addAttribute("list",typeDao.findAll());
        return "menu_add";
    }

    @RequestMapping("/save")
    public String save(Menu menu){
        menuDao.save(menu);
        return "menu_manage";
    }

    @RequestMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu",menuDao.findById(id));
        modelAndView.addObject("list",typeDao.findAll());
        modelAndView.setViewName("menu_update");
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Menu menu){
        menuDao.update(menu);
        return "redirect:/account/redirect/menu_manage";
    }

    @RequestMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        if(!(menuDao.findOrderById(id).isEmpty())){
            return "menu_manage";
        }
        menuDao.save1(menuDao.findById(id));
        menuDao.deleteById(id);
        return "menu_manage";
    }
}

