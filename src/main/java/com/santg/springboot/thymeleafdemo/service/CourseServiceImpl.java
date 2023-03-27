package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.dao.CourseRepository;
import com.santg.springboot.thymeleafdemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAllByOrderById();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        if(course.getId() != 0){
            Course course1 = courseRepository.getOne(course.getId());
            course1.setTitle(course.getTitle());
            course1.setDescription(course.getDescription());
        }else{
            courseRepository.save(course);
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
