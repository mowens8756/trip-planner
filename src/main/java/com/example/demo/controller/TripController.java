package com.example.demo.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.ItineraryCreateForm;
import com.example.demo.form.ItineraryUpdateForm;
import com.example.demo.form.TripCreateForm;
import com.example.demo.form.TripUpdateForm;
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
	private final String EDIT_TRIP_TEMPLATE_PATH = "/trip_planner/trip_plan/edit_trip";
	private final String SHOW_ITINERARY_TEMPLATE_PATH = "/trip_planner/trip_plan/show_itinerary";
	private final String REDIRECT_SHOW_URL = "redirect:/trip_planner/trip_plan/show_trip";
	
	@GetMapping("new_trip")
	public String plan(@ModelAttribute TripCreateForm tripCreateForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		tripCreateForm.setUsername(userDetails.getUsername());
		List<ItineraryCreateForm> itinerary = new ArrayList<ItineraryCreateForm>();
		for (int i = 0; i < 10; ++i) {
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
		return REDIRECT_SHOW_URL;
	}
	
	@GetMapping("show_trip")
	public String show(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		String username = userDetails.getUsername();
		model.addAttribute("trips", tripService.findAllByUsername(username));
		return SHOW_TRIP_TEMPLATE_PATH;
	}
	
	@PostMapping("delete/{trip_id}")
	public String destroy(@PathVariable Integer trip_id) {
		itineraryService.delete(trip_id);
		tripService.delete(trip_id);
		return REDIRECT_SHOW_URL;
	}

	@GetMapping("edit/{trip_id}")
	public String edit(@PathVariable Integer trip_id, @ModelAttribute TripUpdateForm tripUpdateForm, Model model) {
		Trip trip = tripService.findOne(trip_id);
		List<Itinerary> itinerary = itineraryService.findAllByTripId(trip_id);
		List<ItineraryUpdateForm> itineraryForms = new ArrayList<ItineraryUpdateForm>();
		for (int i = 0; i < itinerary.size(); ++i) {
			ItineraryUpdateForm itineraryForm = new ItineraryUpdateForm();
			itineraryForm.setItinerary_id(itinerary.get(i).getItinerary_id());
			itineraryForm.setItinerary_date(itinerary.get(i).getItinerary_date());
			itineraryForm.setStart_at(itinerary.get(i).getStart_at());
			itineraryForm.setEnd_at(itinerary.get(i).getEnd_at());
			itineraryForm.setLocation(itinerary.get(i).getLocation());
			itineraryForm.setNote(itinerary.get(i).getNote());
			itineraryForm.setAmount(itinerary.get(i).getAmount());
			itineraryForms.add(itineraryForm);
		}
		TripUpdateForm tripForms = new TripUpdateForm();
		tripForms.setTrip_id(trip.getTrip_id());
		tripForms.setTitle(trip.getTitle());
		tripForms.setDestination(trip.getDestination());
		tripForms.setTravel_days(trip.getTravel_days());
		tripForms.setCurrency(trip.getCurrency());
		tripForms.setUsername(trip.getUsername());
		tripForms.setItineraryUpdateForm(itineraryForms);
		tripUpdateForm.setItineraryUpdateForm(itineraryForms);
		model.addAttribute("tripUpdateForm", tripForms);
		return EDIT_TRIP_TEMPLATE_PATH;
	}
	
	@PostMapping("update_trip")
	public String update(@Validated @ModelAttribute TripUpdateForm tripUpdateForm, final BindingResult result, Model model) {
		if (result.hasErrors()) {
			return EDIT_TRIP_TEMPLATE_PATH;
		}
		Trip trip = tripService.findOne(tripUpdateForm.getTrip_id());
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		trip.setUpdated_at(currentTime);
		trip.setCreated_at(trip.getCreated_at());
		trip.setTrip_id(tripUpdateForm.getTrip_id());
		trip.setUsername(tripUpdateForm.getUsername());
		trip.setTitle(tripUpdateForm.getTitle());
		trip.setDestination(tripUpdateForm.getDestination());
		trip.setTravel_days(tripUpdateForm.getTravel_days());
		trip.setCurrency(tripUpdateForm.getCurrency());
		Trip trip2 =  tripService.save(trip);
		List<ItineraryUpdateForm> itineraryUpdateForm = tripUpdateForm.getItineraryUpdateForm();
		for (ItineraryUpdateForm itineraryForm : itineraryUpdateForm) { 
			Itinerary itinerary = new Itinerary();
			Timestamp currentTime2 = new Timestamp(System.currentTimeMillis());
			itinerary.setUpdated_at(currentTime2);
			itinerary.setCreated_at(trip2.getCreated_at());
			itinerary.setTrip_id(trip2.getTrip_id());
			itinerary.setUsername(tripUpdateForm.getUsername());
			itinerary.setItinerary_date(itineraryForm.getItinerary_date());
			itinerary.setStart_at(itineraryForm.getStart_at());
			itinerary.setEnd_at(itineraryForm.getEnd_at());
			itinerary.setLocation(itineraryForm.getLocation());
			itinerary.setNote(itineraryForm.getNote());
			itinerary.setAmount(itineraryForm.getAmount());
			itinerary.setItinerary_id(itineraryForm.getItinerary_id());
			itineraryService.save(itinerary);
		}
		return REDIRECT_SHOW_URL;
	}
	
	@GetMapping("show/{trip_id}")
	public String show(@PathVariable Integer trip_id, Model model) {
		Trip tripWithId = tripService.findOne(trip_id);
		Trip trip = new Trip();
		trip.setTrip_id(tripWithId.getTrip_id());
		trip.setTitle(tripWithId.getTitle());
		trip.setDestination(tripWithId.getDestination());
		trip.setTravel_days(tripWithId.getTravel_days());
		trip.setCurrency(tripWithId.getCurrency());
		trip.setUsername(tripWithId.getUsername());
		model.addAttribute("trip", trip);
		List<Itinerary> itineraryWithId = itineraryService.findAllByTripId(trip_id);
		List<Itinerary> itineraryList = new ArrayList<Itinerary>();
		for (int i = 0; i < itineraryWithId.size(); ++i) {
			Itinerary itinerary = new Itinerary();
			itinerary.setItinerary_id(itineraryWithId.get(i).getItinerary_id());
			itinerary.setItinerary_date(itineraryWithId.get(i).getItinerary_date());
			itinerary.setStart_at(itineraryWithId.get(i).getStart_at());
			itinerary.setEnd_at(itineraryWithId.get(i).getEnd_at());
			itinerary.setLocation(itineraryWithId.get(i).getLocation());
			itinerary.setNote(itineraryWithId.get(i).getNote());
			itinerary.setAmount(itineraryWithId.get(i).getAmount());
			itineraryList.add(itinerary);
		}
		model.addAttribute("itinerary", itineraryList);
		BigDecimal totalAmount = new BigDecimal("0.0");
		for (int i = 0; i < itineraryWithId.size(); ++i) {
			BigDecimal amount = itineraryWithId.get(i).getAmount();
			if(amount != null) {
				totalAmount = totalAmount.add(amount);
			}
		}
		if(totalAmount.compareTo(BigDecimal.ZERO)==0){
			model.addAttribute("totalAmount", totalAmount.setScale(0, RoundingMode.DOWN));
		}else {
			model.addAttribute("totalAmount", totalAmount.setScale(2, RoundingMode.HALF_UP));
		}
		return SHOW_ITINERARY_TEMPLATE_PATH;
	}
	
	@PostMapping("set_public/{trip_id}")
	public String setPublic(@PathVariable Integer trip_id) {
		tripService.setPublic(trip_id);
		return REDIRECT_SHOW_URL;
	}
	
	@PostMapping("set_private/{trip_id}")
	public String setPrivate(@PathVariable Integer trip_id) {
		tripService.setPrivate(trip_id);
		return REDIRECT_SHOW_URL;
	}
}
