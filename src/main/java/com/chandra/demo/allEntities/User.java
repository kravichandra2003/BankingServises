package com.chandra.demo.allEntities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id 
	@GeneratedValue
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long userid;
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
	@JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
	private  boolean loggedIn;
	
	@JsonFormat(shape = JsonFormat.Shape.ANY)
   // @NotNull
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accounts")
    private Accounts accounts;
	
	@JsonFormat(shape = JsonFormat.Shape.ANY)
   // @NotNull
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "beneficiarys")
	private Beneficiary beneficiary;
}