package com.santg.springboot.thymeleafdemo.dao;

import com.santg.springboot.thymeleafdemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByOrderById();
}
