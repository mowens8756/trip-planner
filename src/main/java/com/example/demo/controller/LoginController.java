package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip_planner")
public class LoginController {
	
	private final String LOGIN_TEMPLATE_PATH = "/trip_planner/login";
	private final String REDIRECT_HOME_URL = "redirect:/trip_planner/home";

	@RequestMapping("login")
	public String login() {
		return LOGIN_TEMPLATE_PATH;
	}
	
	@RequestMapping("success")
	public String success(@PathVariable String username, Model model) {
		return REDIRECT_HOME_URL;
	}
	
	@RequestMapping("/")
	public String home() {
		return REDIRECT_HOME_URL;
	}

}
