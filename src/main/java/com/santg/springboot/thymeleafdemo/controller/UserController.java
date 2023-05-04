package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.repository.UserRepository;
import com.santg.springboot.thymeleafdemo.entity.Role;
import com.santg.springboot.thymeleafdemo.entity.User;
import com.santg.springboot.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String userList(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);
        return "user/user-list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId){
        userService.deleteUser(userId);
        return "redirect:/user/list";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("userId") Long userId,Model model){
        User user = userService.getUserById(userId);
        List<Role> roleList = Arrays.asList(Role.values());

        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        return "user/add_user";
    }

    @GetMapping("/add")
    public String getUserAddPage(Model model){
        User user = new User();
        List<Role> roleList = Arrays.asList(Role.values());

        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        return "user/add_user";
    }

    @PostMapping(value = "/save")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("authorities") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ROLE_ADMIN")) {
                Role role_admin = Role.ROLE_ADMIN;
                roleSet.add(role_admin);
            }
            if (st.equals("ROLE_USER")) {
                Role role_user = Role.ROLE_USER;
                roleSet.add(role_user);
            }
        }
        user.setRoleSet(roleSet);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user/list";
    }
}
