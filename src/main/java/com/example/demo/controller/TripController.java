package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	private final String NEW_TRIP_TEMPLATE_PATH = "/trip_planner/trip_plan/new_trip";
	private final String SHOW_TRIP_TEMPLATE_PATH = "/trip_planner/trip_plan/show_trip";
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
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		trip.setCreated_at(currentTime);
		trip.setUpdated_at(currentTime);
		trip.setUsername(tripCreateForm.getUsername());
		trip.setTitle(tripCreateForm.getTitle());
		trip.setDestination(tripCreateForm.getDestination());
		trip.setTravel_days(tripCreateForm.getTravel_days());
		trip.setCurrency(tripCreateForm.getCurrency());
		Trip trip2 =  tripService.save(trip);
		List<ItineraryCreateForm> itineraryCreateForm = tripCreateForm.getItineraryCreateForm();
		for (ItineraryCreateForm itineraryForm : itineraryCreateForm) { 
			Itinerary itinerary = new Itinerary();
			Timestamp currentTime2 = new Timestamp(System.currentTimeMillis());
			itinerary.setCreated_at(currentTime2);
			itinerary.setUpdated_at(currentTime2);
			itinerary.setTrip_id(trip2.getTrip_id());
			itinerary.setUsername(tripCreateForm.getUsername());
			itinerary.setItinerary_date(itineraryForm.getItinerary_date());
			itinerary.setStart_at(itineraryForm.getStart_at());
			itinerary.setEnd_at(itineraryForm.getEnd_at());
			itinerary.setLocation(itineraryForm.getLocation());
			itinerary.setNote(itineraryForm.getNote());
			itinerary.setAmount(itineraryForm.getAmount());
			itineraryService.save(itinerary);
		}
		return REDIRECT_HOME_URL;
	}
	@GetMapping("show_trip")
	public String showTripList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		String username = userDetails.getUsername();
		model.addAttribute("trips", tripService.findAllByUsername(username));
		return SHOW_TRIP_TEMPLATE_PATH;
	}
}
