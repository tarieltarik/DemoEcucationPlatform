package com.santg.springboot.thymeleafdemo.dao;

import com.santg.springboot.thymeleafdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u join u.roleSet us where u.username = ?1")
    Optional<User> findByUsername(String name);

    List<User>  getAllByOrderById();
}
