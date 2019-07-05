package com.santg.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santg.springboot.thymeleafdemo.model.Movie;

@Controller
@RequestMapping("/movies")
public class MovieController {

	 // load movies data
	 private List<Movie> movies;
	 
	 @PostConstruct
	 private void loadData() {
		 
		 // create movie
		 Movie movie1 = new Movie(1, "Toy Story", "Pixar", "Fantasy");
		 Movie movie2 = new Movie(2, "Spider-Man", "Marvel", "Science Fiction");
		 Movie movie3 = new Movie(3, "Harry Potter", "Warner", "Fantasy");
		 
		 // create list
		 movies = new ArrayList<>();
		 
		 // add to list
		 movies.add(movie1);
		 movies.add(movie2);
		 movies.add(movie3);
	 }
	 
	 // add mapping for "/list"
	 @GetMapping("/list")
	 public String moviesList(Model model) {
		 
		 // add to the spring model
		 model.addAttribute("movies", movies);
		 
		 return "movies-list";
	 }
}
