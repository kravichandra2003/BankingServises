package com.chandra.demo.allEntities;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BalanceInfo {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank
	private String userName;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
	@NotNull
	private BigDecimal accountBalance;

}
