package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	private final String REDIRECT_HOME_URL = "redirect:/trip_planner/home";
	
	@GetMapping("new_trip")
	public String plan(@ModelAttribute TripCreateForm tripCreateForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		tripCreateForm.setUsername(userDetails.getUsername());
		List<ItineraryCreateForm> itinerary = new ArrayList<ItineraryCreateForm>();
		for (int i = 0; i < 5; ++i) {
			itinerary.add(new ItineraryCreateForm());
		}
		tripCreateForm.setItineraryCreateForm(itinerary);
		return NEW_TRIP_TEMPLATE_PATH;
	}
	
	@PostMapping("create_trip")
	public String process(@Validated @ModelAttribute TripCreateForm tripCreateForm, final BindingResult result) {
		if (result.hasErrors()) {
			return NEW_TRIP_TEMPLATE_PATH;
		}
		Trip trip = new Trip();
		Itinerary itinerary = new Itinerary();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		trip.setCreated_at(currentTime);
		trip.setUpdated_at(currentTime);
		itinerary.setCreated_at(currentTime);
		itinerary.setUpdated_at(currentTime);
		trip.setUsername(tripCreateForm.getUsername());
		trip.setTitle(tripCreateForm.getTitle());
		trip.setDestination(tripCreateForm.getDestination());
		trip.setTravel_days(tripCreateForm.getTravel_days());
		trip.setCurrency(tripCreateForm.getCurrency());
		tripService.save(trip);
		itinerary.setTrip_id(trip.getTrip_id());
		itinerary.setUsername(tripCreateForm.getUsername());
		
		itineraryService.save(itinerary);
		return REDIRECT_HOME_URL;
	}
}
