package com.chandra.demo.allEntities;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogingInfo {

	@Id
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
}
