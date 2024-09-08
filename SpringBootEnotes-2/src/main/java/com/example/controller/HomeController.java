package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.entity.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {

		if(userService.ifExistEmail(user.getEmail())) {
			session.setAttribute("message", "This account already exist");
		}
		else {
		    User savedUser = userService.saveUser(user);
		    
		    if(savedUser != null) {
		    	session.setAttribute("message", "Registration completed successfully");
		    }
		    else {
		    	session.setAttribute("message", "Something wrong on server");
		    }		    
		}		
//		System.out.println(user);
		return "redirect:/register";
	}
	

//	@GetMapping("/viewNotes")
//	public String viewNotes() {
//		return "viewNotes";
//	}




}
