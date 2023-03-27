package com.santg.springboot.thymeleafdemo.controller;


import com.santg.springboot.thymeleafdemo.entity.Course;
import com.santg.springboot.thymeleafdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DemoController {

	@Autowired
	private CourseService courseService;


	@GetMapping
	public String indexPage(Model model){
		List<Course> courseList = courseService.getAllCourse();
		model.addAttribute("courseList",courseList);
		return "index";
	}

	@GetMapping("/hello")
	public String sayHello(Model model) {
		model.addAttribute("date", new java.util.Date());
		return "helloworld";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
