package com.chandra.demo.allEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Beneficiary {

	@Id
	@GeneratedValue
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotEmpty
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	@NotNull
	private Long accountNumber;
	
}
