package com.example.demo.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccessListForm implements Serializable{
	
	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@Valid
	private List<AccessUpdateForm> accessUpdateForm;
	
}
