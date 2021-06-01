package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "itineraries")
public class Itinerary {
	@Id
	@Column(name = "itinerary_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itinerary_id;
	
	@Column(name ="trip_id", nullable = false)
	private Integer trip_id;
	
	@Column(name = "username", length = 30, nullable = false)
	private String username;
	
	@Column(name = "itinerary_date", nullable = false)
	private Date itinerary_date;
	
	@Column(name = "start_at", nullable = false)
	private Time start_at;
	
	@Column(name = "end_at", nullable = false)
	private Time end_at;
	
	@Column(name = "location", length = 200, nullable = false)
	private String location;
	
	@Lob
	@Column(name = "note", nullable = true)
	private String note;
	
	@Column(name = "amount", nullable = true)
	private BigDecimal amount;
	
	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;
	
	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;
	
}
