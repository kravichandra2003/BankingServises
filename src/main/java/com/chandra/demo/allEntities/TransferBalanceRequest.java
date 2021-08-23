package com.chandra.demo.allEntities;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferBalanceRequest {

	 private String fromAccountNumber;

	    private String toAccountNumber;

	    private BigDecimal amount;
}
