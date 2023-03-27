package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();

    void deleteUser(Long userId);
    void updateOrSave(User user,String[] authorities);
    User getUserById(Long id);
}
