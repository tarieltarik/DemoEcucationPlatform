package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.entity.Course;
import com.santg.springboot.thymeleafdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String getCourseList(Model model){
        List<Course> courseList = courseService.getAllCourse();
        model.addAttribute("couresList", courseList);
        return "course/courses";
    }

    @GetMapping("/add")
    public String addCourse(Model model) {
        Course newCourse = new Course();
        model.addAttribute("course",newCourse);
        return "course/course-save";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/course/list";
    }

    @GetMapping("/update")
    public String updateCourse(@RequestParam("courseId") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "course/course-save";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courseId") long id) {
        courseService.deleteCourseById(id);
        return "redirect:/course/list";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") long id,Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "course/course-details";
    }
}
