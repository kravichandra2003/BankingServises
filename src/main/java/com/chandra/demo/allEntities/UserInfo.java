package com.chandra.demo.allEntities;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserInfo {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank
    private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotBlank
    //@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",message="Please check email")
    private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotBlank
	private String userName;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank
	private String password;
}
