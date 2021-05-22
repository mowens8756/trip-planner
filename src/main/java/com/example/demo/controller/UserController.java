package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UserCreateForm;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.model.SiteUser;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/success")
	public String success(Authentication loginUser, Model model) {
		return "redirect:/userhome";
	}
	@RequestMapping("/userhome")
	public String showUserDetails(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		String username = loginUser.getName();
		model.addAttribute("loginUser", service.findOne(username));
		return "user";
	}
	
	@RequestMapping("/")
	public String success() {
		return "redirect:/userhome";
	}

	@GetMapping("/admin/list")
	public String showAdminList(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("users", service.findAll());
		return "list";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute UserCreateForm userCreateForm) {
		return "register";
	}

	@PostMapping("/create")
	public String process(@Validated @ModelAttribute UserCreateForm userCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		SiteUser user = userCreateForm.toEntity();
		service.save(user);
		return "redirect:/login?register";
	}
	@GetMapping("/edit")
	public String edit(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		String username = loginUser.getName();
		SiteUser user = service.findOne(username);
		model.addAttribute("userUpdateForm", new UserUpdateForm(user));
		return "edit";
	}
	@PostMapping("/update")
	public String update(@Validated @ModelAttribute UserUpdateForm userUpdateForm, final BindingResult result, Authentication loginUser, Model model) {
		if(result.hasErrors()) {
			return "edit";
		}
		SiteUser user = userUpdateForm.toEntity();
		service.save(user);
		return "redirect:/userhome";
	}
}
