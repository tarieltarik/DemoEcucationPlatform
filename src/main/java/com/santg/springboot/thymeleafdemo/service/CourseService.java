package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    Course getCourseById(Long id);
    void saveCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(Long id);
}
