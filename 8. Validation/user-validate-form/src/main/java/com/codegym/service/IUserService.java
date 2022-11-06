package com.codegym.service;

import com.codegym.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUserService {
    List<User> findAll();
    void save(User user);
}
