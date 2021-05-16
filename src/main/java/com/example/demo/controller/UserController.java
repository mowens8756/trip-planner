package com.example.demo.controller;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.SiteUser;
import com.example.demo.service.UserService;
import com.example.demo.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	@Autowired
	UserService service;

	/**
	 * PasswordEncoderクラス.
	 */
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/success")
	public String success(Authentication loginUser, Model model) {
		return "redirect:/home";
	}
	@RequestMapping("/home")
	public String showList(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		String username = loginUser.getName();
		model.addAttribute("loginUser", service.findOne(username));
		return "user";
	}
	
	@RequestMapping("/")
	public String success() {
		return "redirect:/home";
	}

	@GetMapping("/admin/list")
	public String showAdminList(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("users", service.findAll());
		return "list";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("user") SiteUser user) {
		return "register";
	}

	@PostMapping("/register")
	public String process(@Validated @ModelAttribute("user") SiteUser user, BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		}

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		user.setCreated_at(currentTime);
		user.setUpdated_at(currentTime);
		if (user.isAdmin()) {
			user.setRole(Role.ADMIN.name());
		} else {
			user.setRole(Role.USER.name());
		}
		user.setActive(true);
		service.save(user);

		return "redirect:/login?register";
	}
}
