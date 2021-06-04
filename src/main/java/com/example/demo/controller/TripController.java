package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.ItineraryCreateForm;
import com.example.demo.form.TripCreateForm;
import com.example.demo.model.Itinerary;
import com.example.demo.model.Trip;
import com.example.demo.model.impl.UserDetailsImpl;
import com.example.demo.service.ItineraryService;
import com.example.demo.service.TripService;

@Controller
@RequestMapping("/trip_planner/trip_plan")
public class TripController {
	
	@Autowired
	private TripService tripService;
	@Autowired
	private ItineraryService itineraryService;
	@Autowired
	HttpSession session;
	
	private final String NEW_TRIP_TEMPLATE_PATH = "/trip_planner/trip_plan/new_trip";
	private final String NEW_ITINERARY_TEMPLATE_PATH = "/trip_planner/trip_plan/new_itinerary";
	private final String REDIRECT_HOME_URL = "redirect:/trip_planner/home";
	
	@GetMapping("new_trip")
	public String plan(@ModelAttribute TripCreateForm tripCreateForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		tripCreateForm.setUsername(userDetails.getUsername());
		return NEW_TRIP_TEMPLATE_PATH;
	}
	
	@PostMapping("create_trip")
	public String process(@Validated @ModelAttribute TripCreateForm tripCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return NEW_TRIP_TEMPLATE_PATH;
		}
		Trip trip = tripCreateForm.toEntity();
		session.setAttribute("trip", trip);
		return NEW_ITINERARY_TEMPLATE_PATH;
	}
	
	@GetMapping("new_itinerary")
	public String plan(@ModelAttribute ItineraryCreateForm itineraryCreateForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		itineraryCreateForm.setUsername(userDetails.getUsername());
		return NEW_ITINERARY_TEMPLATE_PATH;
	}
	
	@PostMapping("create_itinerary")
	public String process(@Validated @ModelAttribute ItineraryCreateForm itineraryCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return NEW_ITINERARY_TEMPLATE_PATH;
		}
		Trip trip = (Trip) session.getAttribute("trip");
		itineraryCreateForm.setTrip_id(trip.getTrip_id());
		tripService.save(trip);
		Itinerary itinerary = itineraryCreateForm.toEntity();
		itineraryService.save(itinerary);
		session.removeAttribute("trip");
		return REDIRECT_HOME_URL;
	}
}
