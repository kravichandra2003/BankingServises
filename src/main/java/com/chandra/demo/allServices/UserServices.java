package com.chandra.demo.allServices;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chandra.demo.allEntities.Accounts;
import com.chandra.demo.allEntities.User;
import com.chandra.demo.allEntities.UserInfo;
import com.chandra.demo.allEntities.UserLogingInfo;
import com.chandra.demo.allEnums.Status;
import com.chandra.demo.allRepository.AccountsRepo;
import com.chandra.demo.allRepository.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountsRepo accountsRepo;

	public ResponseEntity<Map<String, Object>> registerUser(UserInfo userInfo) {
		Accounts account = new Accounts();
		account.setAccountNumber(randomAccountNumber());
		account.setAccountBalance(new BigDecimal(10000.00));
		Map<String, Object> body = new LinkedHashMap<>();
		User newUser = userRepository.findByUserNameIgnoreCase(userInfo.getUserName());
		if (newUser != null && newUser.getUserName().equalsIgnoreCase(userInfo.getUserName())) {
			body.put("timestamp", LocalDateTime.now());
			body.put(newUser.getName(), Status.USER_ALREADY_EXISTS);
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.ALREADY_REPORTED);
		}
		User user1  = new User();
		user1.setName(userInfo.getName());
		user1.setUserName(userInfo.getUserName());
		user1.setPassword(userInfo.getPassword());
		user1.setEmail(userInfo.getEmail());
		user1.setAccounts(account);
		userRepository.save(user1);
		User user = userRepository.findByUserNameIgnoreCase(userInfo.getUserName());
		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.SUCCESS);
		body.put("Name:- ", user.getName());
		body.put("UserName:- ", user.getUserName());
		body.put("Password:- ", user.getPassword());
		body.put("Account:- ", user.getAccounts().getAccountNumber());
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.ACCEPTED);
	}
	
	public long randomAccountNumber() {
	    Random random = new Random();
	    char[] digits = new char[12];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < digits.length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}

	public ResponseEntity<Map<String, Object>> loginUser(UserLogingInfo logingInfo) {
		Map<String, Object> body = new LinkedHashMap<>();
		User user = userRepository.findByUserNameIgnoreCase(logingInfo.getUserName());
		if (user != null) {
			if (user.getUserName().equals(logingInfo.getUserName())) {
				user.setLoggedIn(true);
				userRepository.save(user);
				body.put("timestamp", LocalDateTime.now());
				body.put(user.getName(), Status.USER_LOGGED_IN);
				body.put("message", Status.SUCCESS);
				return new ResponseEntity<Map<String, Object>>(body, HttpStatus.ACCEPTED);
			}
		}
		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.FAILURE);
		body.put(logingInfo.getUserName(), Status.USER_DONT_EXISTS);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Map<String, Object>> logUserOut(UserLogingInfo logingInfo) {
		Map<String, Object> body = new LinkedHashMap<>();
		User user = userRepository.findByUserNameIgnoreCase(logingInfo.getUserName());
		if (user != null) {
			if (user.getUserName().equals(logingInfo.getUserName())) {
				user.setLoggedIn(false);
				userRepository.save(user);
				body.put("timestamp", LocalDateTime.now());
				body.put(user.getUserName(), Status.USER_LOGGED_OUT);
				body.put("message", Status.SUCCESS);
				return new ResponseEntity<Map<String, Object>>(body, HttpStatus.ACCEPTED);
			}
		}

		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.FAILURE);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Map<String, Object>> deleteUsers() {
		userRepository.deleteAll();
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", Status.SUCCESS);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}
}
