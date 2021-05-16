package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

/**
 * usersテーブルのEntityクラス.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class SiteUser {

	/**
	 * ユーザー名. プライマリーキー.
	 */
	@Id
	@Column(name = "username", length = 30, nullable = false)
	private String username;


	@Column(name="email", nullable=false)
	private String email;

	/** パスワード. */
	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name="created_at", nullable=false)
	private Timestamp created_at;

	@Column(name="updated_at", nullable=false)
	private Timestamp updated_at;

	/** ロール. */
	@Column(name = "role", length = 120, nullable = false)
	private String role;

	/** 管理者フラグ. */
	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;

	/** 有効フラグ. */
	@Column(name = "is_active", nullable = false)
	private boolean isActive;

}
