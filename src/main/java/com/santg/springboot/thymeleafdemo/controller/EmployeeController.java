package com.santg.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santg.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	 // load movies data
	 private List<Employee> employees;
	 
	 @PostConstruct
	 private void loadData() {
		 
		 // create movie
		 Employee emp1 = new Employee(1, "Liliana", "Vess", "liliana@mail.com");
		 Employee emp2 = new Employee(2, "Jace", "Beleren", "jace@mail.com");
		 Employee emp3 = new Employee(3, "Gideon", "Jura", "gideon@mail.com");
		 
		 // create list
		 employees = new ArrayList<>();
		 
		 // add to list
		 employees.add(emp1);
		 employees.add(emp2);
		 employees.add(emp3);
	 }
	 
	 // add mapping for "/list"
	 @GetMapping("/list")
	 public String employeeList(Model model) {
		 
		 // add to the spring model
		 model.addAttribute("employees", employees);
		 
		 return "list-employees";
	 }
}