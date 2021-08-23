package com.chandra.demo.allController;



import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chandra.demo.allEntities.BalanceInfo;
import com.chandra.demo.allEntities.BeneficiaryInfo;
import com.chandra.demo.allEntities.TransferBalanceRequest;
import com.chandra.demo.allEntities.UserInfo;
import com.chandra.demo.allEntities.UserLogingInfo;
import com.chandra.demo.allServices.AccountServices;
import com.chandra.demo.allServices.BeneficiaryServices;
import com.chandra.demo.allServices.UserServices;

@RestController
public class UserController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	BeneficiaryServices beneficiaryServices;
	
	@Autowired
	AccountServices accountServices;

	@CrossOrigin()
	@PostMapping("/users/register")
	public  ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody UserInfo newUser) {
		
		return userServices.registerUser(newUser);
		
	}

	@CrossOrigin()
	@PostMapping("/users/login")
	public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody UserLogingInfo user) {
		
		return userServices.loginUser(user);
	}

	@CrossOrigin()
	@PostMapping("/users/logout")
	public ResponseEntity<Map<String, Object>> logUserOut(@Valid @RequestBody UserLogingInfo user) {
		
		return userServices.logUserOut(user);
	}
	
	@CrossOrigin()
	@PostMapping("/users/addbeneficiary")
	public ResponseEntity<Map<String, Object>> userAddBeneficiary(@Valid @RequestBody UserLogingInfo user, BeneficiaryInfo beneficiaryUsers) {
		
		return beneficiaryServices.beneficiaryAdd(user, beneficiaryUsers);
	}
	
	@CrossOrigin()
	@PostMapping("/users/addaccountBalance")
	public ResponseEntity<Map<String, Object>> userAddAccountbalance( @RequestBody BalanceInfo balanceInfo) {
		
		return accountServices.accountAdd(balanceInfo);
	}
	
	@CrossOrigin()
	@PostMapping("/users/fundtransfer")
	public ResponseEntity<Map<String, Object>> fundTransferRequest( @RequestBody TransferBalanceRequest request) {
		
		return accountServices.transferRequest(request);
	}

	@CrossOrigin()
	@DeleteMapping("/users/all")
	public ResponseEntity<Map<String, Object>> deleteUsers() {
		
		return userServices.deleteUsers();
	}
}