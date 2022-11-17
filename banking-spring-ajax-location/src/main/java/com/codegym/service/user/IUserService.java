package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    Boolean existsByUsername(String username);
}
