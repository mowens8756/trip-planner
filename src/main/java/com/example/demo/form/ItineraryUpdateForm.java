package com.example.demo.form;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ItineraryUpdateForm implements Serializable{
	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private String itinerary_date;

	@DateTimeFormat(pattern = "HH:mm")
	private String start_at;

	@DateTimeFormat(pattern = "HH:mm")
	private String end_at;

	@Size(max = 200)
	private String location;

	private String note;

	@DecimalMax("9999999999")
	private BigDecimal amount;
}
