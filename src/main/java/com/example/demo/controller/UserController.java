package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminCreateForm;
import com.example.demo.form.UserCreateForm;
import com.example.demo.form.UserDeleteForm;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.model.SiteUser;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/trip_planner/user_account")
public class UserController {
	
	@Autowired
	private UserService service;
	
	private final String REDIRECT_SHOW_URL = "redirect:/trip_planner/user_account/show";
	private final String REDIRECT_LOGIN_URL = "redirect:/trip_planner/login?register";
	private final String REDIRECT_LIST_URL = "redirect:/trip_planner/user_account/admin/list";
	private final String LIST_TEMPLATE_PATH = "/trip_planner/user_account/list";
	private final String RESISTER_TEMPLATE_PATH = "/trip_planner/user_account/register";
	private final String RESISTER_ADMIN_TEMPLATE_PATH = "/trip_planner/user_account/register_admin";
	private final String EDIT_TEMPLATE_PATH = "/trip_planner/user_account/edit";
	private final String DELETE_TEMPLATE_PATH = "/trip_planner/user_account/delete";
	private final String USER_TEMPLATE_PATH = "/trip_planner/user_account/user";
	
	@RequestMapping("show/{username}")
	public String showUserDetails(@PathVariable String username, Model model) {
		SiteUser user = service.findOne(username);
		model.addAttribute("user",user);
		return USER_TEMPLATE_PATH;
	}
	
	@GetMapping("admin/list")
	public String showAdminList(Model model) {
		model.addAttribute("users", service.findAll());
		return LIST_TEMPLATE_PATH;
	}

	@GetMapping("register")
	public String register(@ModelAttribute UserCreateForm userCreateForm) {
		return RESISTER_TEMPLATE_PATH;
	}
	
	@PostMapping("create")
	public String process(@Validated @ModelAttribute UserCreateForm userCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return RESISTER_TEMPLATE_PATH;
		}

		// アカウントを有効にする
		userCreateForm.setActive(true);

		SiteUser user = userCreateForm.toEntity();
		service.save(user);
		return REDIRECT_LOGIN_URL;
	}
	
	@GetMapping("register_admin")
	public String registerAdmin(@ModelAttribute AdminCreateForm adminCreateForm) {
		return RESISTER_ADMIN_TEMPLATE_PATH;
	}
	
	@PostMapping("create_admin")
	public String process(@Validated @ModelAttribute AdminCreateForm adminCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return RESISTER_ADMIN_TEMPLATE_PATH;
		}

		// アカウントを有効にする
		adminCreateForm.setActive(true);

		SiteUser user = adminCreateForm.toEntity();
		service.save(user);
		return REDIRECT_LIST_URL;
	}
	
	@GetMapping("edit/{username}")
	public String edit(@PathVariable String username, Model model) {
		SiteUser user = service.findOne(username);

		model.addAttribute("userUpdateForm", new UserUpdateForm(user));
		return EDIT_TEMPLATE_PATH;
	}
	
	@PostMapping("update")
	public String update(@Validated @ModelAttribute UserUpdateForm userUpdateForm, final BindingResult result, Model model) {
		if(result.hasErrors()) {
			return EDIT_TEMPLATE_PATH;
		}
		SiteUser user = service.findOne(userUpdateForm.getUsername());
		userUpdateForm.setCreated_at(user.getCreated_at());
		userUpdateForm.setRole(user.getRole());
		userUpdateForm.setAdmin(user.isAdmin());
		userUpdateForm.setActive(user.isActive());
		SiteUser updatedUser = userUpdateForm.toEntity();
		service.save(updatedUser);
		return REDIRECT_SHOW_URL +"/" + userUpdateForm.getUsername();
	}
	
	@GetMapping("delete/{username}")
	public String delete(@PathVariable String username, Model model) {
		SiteUser user = service.findOne(username);
		model.addAttribute("userDeleteForm", new UserDeleteForm(user));
		return DELETE_TEMPLATE_PATH;
	}
	
	@PostMapping("deactivate")
	public String deactivate(@Validated @ModelAttribute UserDeleteForm userDeleteForm, final BindingResult result, Model model) {
		if(result.hasErrors()) {
			return DELETE_TEMPLATE_PATH;
		}
		SiteUser user = service.findOne(userDeleteForm.getUsername());
		userDeleteForm.setCreated_at(user.getCreated_at());
		userDeleteForm.setRole(user.getRole());
		userDeleteForm.setAdmin(user.isAdmin());
		userDeleteForm.setActive(false);
		SiteUser deactivatedUser = userDeleteForm.toEntity();
		service.save(deactivatedUser);
		return REDIRECT_LIST_URL;
	}
	
}
