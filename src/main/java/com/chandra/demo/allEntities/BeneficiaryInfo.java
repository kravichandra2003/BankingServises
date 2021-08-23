package com.chandra.demo.allEntities;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BeneficiaryInfo {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String beneficiaryName;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Long beneficiaryAccountNumber;
}
