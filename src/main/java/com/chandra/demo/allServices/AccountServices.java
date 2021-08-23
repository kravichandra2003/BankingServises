package com.chandra.demo.allServices;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chandra.demo.allEntities.Accounts;
import com.chandra.demo.allEntities.BalanceInfo;
import com.chandra.demo.allEntities.TransferBalanceRequest;
import com.chandra.demo.allEntities.User;
import com.chandra.demo.allEnums.Status;
import com.chandra.demo.allRepository.AccountsRepo;
import com.chandra.demo.allRepository.BeneficiaryRepo;
import com.chandra.demo.allRepository.UserRepository;

@Service
public class AccountServices {

	@Autowired
	AccountsRepo accountsRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BeneficiaryRepo beneficiaryRepo;

	public ResponseEntity<Map<String, Object>> accountAdd(BalanceInfo accountInfo) {
		Map<String, Object> body = new LinkedHashMap<>();
		User user = userRepository.findByUserNameIgnoreCase(accountInfo.getUserName());
		if (user!=null && accountInfo.getAccountBalance().compareTo(BigDecimal.ONE) == 1) {
			user.getAccounts().setAccountNumber(user.getAccounts().getAccountNumber());
			user.getAccounts().setAccountBalance(user.getAccounts().getAccountBalance().add(accountInfo.getAccountBalance()));
			user.getAccounts().setId(user.getAccounts().getId());;
			userRepository.save(user);
			User newUser = userRepository.findByUserNameIgnoreCase(accountInfo.getUserName());
			body.put("timestamp", LocalDateTime.now());
			body.put("message", Status.SUCCESS);
			body.put("Balance", newUser.getAccounts().getAccountBalance());
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
		}
		
		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.FAILURE);
		body.put("message", Status.USER_DONT_EXISTS);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Map<String, Object>> transferRequest(TransferBalanceRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		Accounts fromUser = accountsRepo.findByAccountNumber(Long.parseLong(request.getFromAccountNumber()));
		//Beneficiary toUser = beneficiaryRepo.findByAccountNumber(Long.parseLong(request.getToAccountNumber()));

		if (fromUser.getAccountBalance().compareTo(BigDecimal.ONE) == 1
				&& fromUser.getAccountBalance().compareTo(request.getAmount()) == 1) {

			fromUser.setAccountBalance(fromUser.getAccountBalance().subtract(request.getAmount()));
			accountsRepo.save(fromUser);
			body.put("timestamp", LocalDateTime.now());
			body.put("message", Status.SUCCESS);
			body.put("balance", fromUser.getAccountBalance());
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);

		}

		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.FAILURE);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.BAD_REQUEST);
	}

}
