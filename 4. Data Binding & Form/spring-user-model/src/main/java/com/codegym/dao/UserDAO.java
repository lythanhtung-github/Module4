package com.codegym.dao;

import com.codegym.model.Login;
import com.codegym.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("admin123",
                "123",
                "Nguyễn Văn A",
                "nguyenvana@gmail.com",
                20));
        users.add(new User("admin124",
                "124",
                "Nguyễn Văn B",
                "nguyenvanb@gmail.com",
                21));
        users.add(new User("admin125",
                "125",
                "Nguyễn Văn C",
                "nguyenvanc@gmail.com",
                22));
        users.add(new User("admin126",
                "126",
                "Nguyễn Văn D",
                "nguyenvand@gmail.com",
                23));
    }

    public static User checkLogin(Login login) {
        for (User user : users) {
            if (user.getAccount().equals(login.getAccount())
                    && user.getPassword().equals(login.getPassword())){
                return user;
            }
        }
        return null;
    }
}
