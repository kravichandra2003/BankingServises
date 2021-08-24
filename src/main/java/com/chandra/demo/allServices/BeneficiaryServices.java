package com.chandra.demo.allServices;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chandra.demo.allEntities.Beneficiary;
import com.chandra.demo.allEntities.BeneficiaryInfo;
import com.chandra.demo.allEntities.User;
import com.chandra.demo.allEntities.UserLogingInfo;
import com.chandra.demo.allEnums.Status;
import com.chandra.demo.allRepository.BeneficiaryRepo;
import com.chandra.demo.allRepository.UserRepository;

@Service
public class BeneficiaryServices {

	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	@Autowired
	UserRepository userRepository;

	public ResponseEntity<Map<String, Object>> beneficiaryAdd(UserLogingInfo newUser, BeneficiaryInfo newBeneficiaryUsers) {
		Beneficiary beneficiaryUsers = new Beneficiary();
		Map<String, Object> body = new LinkedHashMap<>();
		User user = userRepository.findByUserNameIgnoreCase(newUser.getUserName());
		if (user.isLoggedIn()) {
			//working on it figuring out how to work with mapping of these kind
			//user.getAccounts().getBeneficiaryAccount().setAccountNumber(newBeneficiaryUsers.getBeneficiaryAccountNumber());
			//user.getAccounts().getBeneficiaryAccount().setName(newBeneficiaryUsers.getBeneficiaryName());
			
			//user.getBeneficiary().setAccountNumber(newBeneficiaryUsers.getBeneficiaryAccountNumber());
			//user.getBeneficiary().setName(newBeneficiaryUsers.getBeneficiaryName());
			//user.getBeneficiary().setId(user.getUserid());
			beneficiaryUsers.setAccountNumber(newBeneficiaryUsers.getBeneficiaryAccountNumber());
			beneficiaryUsers.setName(newBeneficiaryUsers.getBeneficiaryName());
			beneficiaryRepo.save(beneficiaryUsers);
			//userRepository.save(user);
			body.put("timestamp", LocalDateTime.now());
			body.put("message", Status.SUCCESS);
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
		}
		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.USER_LOGGED_OUT);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}
}
