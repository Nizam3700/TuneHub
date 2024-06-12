package com.kodnest.tunehub.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		
		String email=user.getEmail();
		    boolean status= userService.emailExists(email);
		if(status==false) {
			userService.addUser(user);
			System.out.println("user added");
		}
		else {
			System.out.println("user already exists");
		}
//		System.out.println(user.getUsername()+" "+user.getEmail()+" "
//				+ " "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());
//		userService.addUser(user);
		return "login";
		
	}

//	@PostMapping("/register")
//	public String addUser(@RequestParam("username") String username,
//			@RequestParam("email") String email,
//			@RequestParam("password") String password,
//			@RequestParam("gender") String gender,
//			@RequestParam("role") String role,
//			@RequestParam("address") String address) {
//		System.out.println(username+" "+email+" "+password+" "+gender+" "+role+" "+address);
//		return "home";
//	}
	
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session, Model model ){
		if(userService.validateUser(email,password)==true) {
			
			String role=userService.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin"))
			return "adminhome";
		
		else {
			User user = userService.getUser(email);
			boolean userstatus = user.isIspremium();
			List<Song> songList = songService.fetchAllSongs();
			model.addAttribute("songs", songList);
			model.addAttribute("ispremium", userstatus);
			return "customerhome";
		}
	}
	else {
		return "login";
	}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/exploresongs")
	public String exploresongs(String email) {
		return email;


	}

}
