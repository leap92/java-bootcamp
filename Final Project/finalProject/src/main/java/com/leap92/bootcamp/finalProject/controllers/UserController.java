package com.leap92.bootcamp.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leap92.bootcamp.finalProject.model.User;
import com.leap92.bootcamp.finalProject.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String userLogin(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String userRegistration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String userRegistration(@ModelAttribute User user, Model model) {
		User ux = userRepository.findByUsername(user.getUsername());
		if (ux != null) {
			return "redirect:/register?alreadyExists";
		}
		else {
			userRepository.save(user);
			return "login";
		}
	}

}
