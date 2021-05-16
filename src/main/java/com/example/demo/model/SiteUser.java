package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class SiteUser {
	@Id
	@Column(name="username", length=30, nullable=false)
	private String username;
	@Email
	@Column(name="email", nullable=false)
	private String email;
	@Column(name="password", length=255, nullable=false)
	private String password;
	@Column(name="created_at", nullable=false)
	private Timestamp created_at;
	@Column(name="updated_at", nullable=false)
	private Timestamp updated_at;
	private String role;
	private boolean admin;
	private boolean active = true;
}
