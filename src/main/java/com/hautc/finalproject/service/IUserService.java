package com.hautc.finalproject.service;

import com.hautc.finalproject.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User findByUsername(String username);

    User insertUser(User user);

    List<User> getAllUser();

    void changePassword(String password, String username);
}
