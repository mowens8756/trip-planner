package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccessUpdateForm implements Serializable{
	
	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Integer access_id;
	@NotNull
	private Integer trip_id;
	@NotBlank
	@Size(max = 30)
	private String username;
	private String approved_users;
	
}
