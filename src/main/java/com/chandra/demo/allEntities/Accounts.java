package com.chandra.demo.allEntities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Accounts {

	@Id 
	@GeneratedValue
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	@NotNull
	private Long accountNumber;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
	@NotNull
	private BigDecimal accountBalance;
	@OneToOne
	@JoinColumn(name = "beneficiaryaccounts")
	private Beneficiary beneficiaryAccount;
}
