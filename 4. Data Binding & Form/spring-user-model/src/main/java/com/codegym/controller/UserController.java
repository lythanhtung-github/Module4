package com.codegym.controller;

import com.codegym.dao.UserDAO;
import com.codegym.model.Login;
import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home", "login", new Login());
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("login") Login login){
        User user = UserDAO.checkLogin(login);
        ModelAndView modelAndView;
        if(user == null){
            modelAndView = new ModelAndView("home");
            modelAndView.addObject("error", "Tài khoản hoặc mật khẩu không đúng");
        }
        else{
            modelAndView = new ModelAndView("info");
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }
}
