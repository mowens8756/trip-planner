package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.TripService;

@Controller
public class LoginController {
	
	@Autowired
	private TripService tripService;
	
	private final String LOGIN_TEMPLATE_PATH = "/trip_planner/login";
	private final String REDIRECT_HOME_URL = "redirect:/trip_planner/home";
	private final String HOME_URL = "/trip_planner/home";
	
	@RequestMapping("/trip_planner/login")
	public String login() {
		return LOGIN_TEMPLATE_PATH;
	}
	
	@RequestMapping("/trip_planner/success")
	public String success(Model model) {
		return REDIRECT_HOME_URL;
	}
	
	@RequestMapping("/")
	public String top() {
		return REDIRECT_HOME_URL;
	}

	@RequestMapping("/trip_planner")
	public String appName() {
		return REDIRECT_HOME_URL;
	}

	@RequestMapping("/trip_planner/home")
	public String home(Model model) {
		model.addAttribute("trips", tripService.findAll());
		return HOME_URL;
	}

}
