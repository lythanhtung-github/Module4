package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class UserController {

    @GetMapping
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/result")
    public ModelAndView result(@Validated @ModelAttribute User user,
                               BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            modelAndView.setViewName("/index");
            return modelAndView;
        }

        modelAndView.setViewName("/result");
        return modelAndView;
    }
}
