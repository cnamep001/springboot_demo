package com.m.controller;

import com.m.dao.AdminDao;
import com.m.dao.UserDao;
import com.m.entity.Admin;
import com.m.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminDao adminDao;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {

        String target = null;
        switch (type) {
            case "user":
                User user = userDao.login(username, password);

                if (user == null) {
                    target = "redirect:/login.html";
                } else {
                    session.setAttribute("user", user);
                    target = "user_main";
                }
                break;
            case "admin":
                Admin admin = adminDao.login(username, password);
                if (admin == null) {
                    target = "redirect:/login.html";
                } else {
                    session.setAttribute("admin", admin);
                    target = "admin_main";
                }
                break;
        }
        return target;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    @RequestMapping("/redirect/{target}")
    public String redirect(@PathVariable("target") String target){
        return target;
    }
}
