package com.codegym.service;

import com.codegym.model.User;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    private static List<User> userList;
    static{
        userList = new ArrayList<>();
        userList.add(new User("Lộc", "Lộc","0987654321",18,"loc@gmail.com"));
        userList.add(new User("Lộc2", "Lộc2","0987654322",19,"loc2@gmail.com"));
    }
    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }
}
