package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping("/users")
    public String showUserList(Model model){
        List<User> userList = new ArrayList<>();
        userList.add(new User(0,"Lộc Ngáo", "Phạm Thị Liên", 1));
        userList.add(new User(1,"Lộc Ngáo 1", "Phạm Thị Liên", 2));
        userList.add(new User(2,"Lộc Ngáo 2", "Phạm Thị Liên", 3));
        userList.add(new User(3,"Lộc Ngáo 3", "Phạm Thị Liên", 1));
        model.addAttribute("userList", userList);
        return "list-user";
    }
}
