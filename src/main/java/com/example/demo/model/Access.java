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
@Table(name = "accesses")
public class Access {
	@Id
	@Column(name = "access_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer access_id;
	
	@Column(name ="trip_id", nullable = false)
	private Integer trip_id;
	
	@Column(name = "username", length = 30, nullable = false)
	private String username;
	
	@Column(name = "approved_users", nullable = true)
	private String approved_users;
	
	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;
	
	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;
	
}
