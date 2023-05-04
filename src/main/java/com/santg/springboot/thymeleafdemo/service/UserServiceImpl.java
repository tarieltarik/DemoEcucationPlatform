package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.repository.UserRepository;
import com.santg.springboot.thymeleafdemo.entity.Role;
import com.santg.springboot.thymeleafdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(username);
        return user.orElse(null);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllByOrderById();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateOrSave(User user, String[] roles) {
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
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }
}
