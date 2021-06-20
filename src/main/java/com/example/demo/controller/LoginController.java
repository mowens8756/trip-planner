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
	private final String REDIRECT_LANDING_URL = "redirect:/trip_planner/landing_page";
	private final String LANDING_URL = "/trip_planner/landing_page";
	
	
	@RequestMapping("/")
	public String top() {
		return REDIRECT_LANDING_URL;
	}
	
	@RequestMapping("/trip_planner/")
	public String moveToTop() {
		return REDIRECT_LANDING_URL;
	}
	

	@RequestMapping("/trip_planner")
	public String landOnTop() {
		return REDIRECT_LANDING_URL;
	}
	
	@RequestMapping("/trip_planner/landing_page")
	public String land() {
		return LANDING_URL;
	}
	
	@RequestMapping("/trip_planner/login")
	public String login() {
		return LOGIN_TEMPLATE_PATH;
	}
	
	@RequestMapping("/trip_planner/success")
	public String success(Model model) {
		return REDIRECT_HOME_URL;
	}
	
	@RequestMapping("/trip_planner/home")
	public String home(Model model) {
		model.addAttribute("trips", tripService.findAllPublic());
		return HOME_URL;
	}

}
