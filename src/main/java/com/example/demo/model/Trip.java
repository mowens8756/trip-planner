package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
	@Id
	@Column(name = "trip_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trip_id;
	
	@Column(name = "username", length = 30, nullable = false)
	private String username;
	
	@Column(name = "title", length = 255, nullable = false)
	private String title;
	
	@Column(name = "destination", length = 100, nullable = false)
	private String destination;
	
	@Column(name = "travel_days", nullable = false)
	private Double travel_days;
	
	@Column(name = "currency", nullable = false)
	private int currency;
	
	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;
	
	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;
	
}
