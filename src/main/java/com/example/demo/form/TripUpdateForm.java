package com.example.demo.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TripUpdateForm implements Serializable{

	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(max = 30)
	private String username;
	@NotBlank
	@Size(max = 255)
	private String title;
	@NotBlank
	@Size(max = 100)
	private String destination;
	@NotNull
	private Double travel_days;
	private int currency;

	@Valid
	private List<ItineraryUpdateForm> itineraryUpdateForm;
}
