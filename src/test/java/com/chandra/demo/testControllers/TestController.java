package com.chandra.demo.testControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chandra.demo.allController.UserController;
import com.chandra.demo.allEntities.AccountInfo;
import com.chandra.demo.allEntities.BalanceInfo;
import com.chandra.demo.allEntities.BeneficiaryInfo;
import com.chandra.demo.allEntities.TransferBalanceRequest;
import com.chandra.demo.allEntities.UserInfo;
import com.chandra.demo.allEntities.UserLogingInfo;
import com.chandra.demo.allEnums.Status;
import com.chandra.demo.allServices.AccountServices;
import com.chandra.demo.allServices.BeneficiaryServices;
import com.chandra.demo.allServices.UserServices;

@ExtendWith(MockitoExtension.class)
public class TestController {

	@Mock
	UserServices userServices;

	@Mock
	BeneficiaryServices beneficiaryServices;

	@Mock
	AccountServices accountServices;

	@InjectMocks
	UserController userController;

	static AccountInfo accountInfo;
	static BalanceInfo balanceInfo;
	static BeneficiaryInfo beneficiaryInfo;
	static TransferBalanceRequest transferBalanceRequest;
	static UserInfo userInfo;
	static UserLogingInfo userLogingInfo;

	@BeforeAll
	public static void setUp() {
		accountInfo = new AccountInfo();
		accountInfo.setUserName("123ravi");
		accountInfo.setAccountNumber(234567898765l);
		accountInfo.setAccountBalance(new BigDecimal(1000));

		userInfo = new UserInfo();
		userInfo.setEmail("ravi@email.com");
		userInfo.setName("ravi");
		userInfo.setUserName("123ravi");
		userInfo.setPassword("ravi123");

		balanceInfo = new BalanceInfo();
		balanceInfo.setAccountBalance(new BigDecimal(1000));
		balanceInfo.setUserName("123ravi");

		beneficiaryInfo = new BeneficiaryInfo();
		beneficiaryInfo.setBeneficiaryAccountNumber(234567898765l);
		beneficiaryInfo.setBeneficiaryName("ravi");

		transferBalanceRequest = new TransferBalanceRequest();
		transferBalanceRequest.setAmount(new BigDecimal(1000));
		transferBalanceRequest.setFromAccountNumber("234567897654");
		transferBalanceRequest.setToAccountNumber("234567896543");

		userLogingInfo = new UserLogingInfo();
		userLogingInfo.setPassword("ravi123");
		userLogingInfo.setUserName("123ravi");
	}

	@Test
	@DisplayName("Reg Function: positive Scenario")
	public void registerUser() {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.SUCCESS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.ACCEPTED);
		Mockito.when(userServices.registerUser(userInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.registerUser(userInfo);

		// outcome
		assertEquals(responseEntity, result);

	}

	@Test
	@DisplayName("Reg Function: negitive Scenario")
	public void registerUser1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_ALREADY_EXISTS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.BAD_REQUEST);
		Mockito.when(userServices.registerUser(userInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.registerUser(userInfo);

		// outcome
		assertEquals(responseEntity, result);

	}

	@Test
	@DisplayName("login user: positive Scenario")
	public void loginUser() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_LOGGED_IN);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.OK);
		Mockito.when(userServices.loginUser(userLogingInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.loginUser(userLogingInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("login user: negitive Scenario")
	public void loginUser1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_DONT_EXISTS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.NOT_FOUND);
		Mockito.when(userServices.loginUser(userLogingInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.loginUser(userLogingInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("logOut user: positive Scenario")
	public void logUserOut() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_LOGGED_OUT);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.OK);
		Mockito.when(userServices.logUserOut(userLogingInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.logUserOut(userLogingInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("logOut user: negitive Scenario")
	public void logUserOut1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_DONT_EXISTS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.NOT_FOUND);
		Mockito.when(userServices.logUserOut(userLogingInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.logUserOut(userLogingInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("Beneficiary add: positive Scenario")
	public void userAddBeneficiary() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.SUCCESS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.NOT_FOUND);
		Mockito.when(beneficiaryServices.beneficiaryAdd(userLogingInfo, beneficiaryInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.userAddBeneficiary(userLogingInfo, beneficiaryInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("Beneficiary add: negitive Scenario")
	public void userAddBeneficiary1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_LOGGED_OUT);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.NOT_FOUND);
		Mockito.when(beneficiaryServices.beneficiaryAdd(userLogingInfo, beneficiaryInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.userAddBeneficiary(userLogingInfo, beneficiaryInfo);

		// outcome
		assertEquals(responseEntity, result);
	}

	@Test
	@DisplayName("Accountbalance add Function: positive Scenario")
	public void userAddAccountbalance() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.SUCCESS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.OK);
		Mockito.when(accountServices.accountAdd(balanceInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.userAddAccountbalance(balanceInfo);

		// outcome
		assertEquals(responseEntity, result);

	}

	@Test
	@DisplayName("Accountbalance add Function: negitive Scenario")
	public void userAddAccountbalance1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.USER_DONT_EXISTS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.BAD_REQUEST);
		Mockito.when(accountServices.accountAdd(balanceInfo)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.userAddAccountbalance(balanceInfo);

		// outcome
		assertEquals(responseEntity, result);

	}

	@Test
	@DisplayName("fundTransfer Request Function: positive Scenario")
	public void fundTransferRequest() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.SUCCESS);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.OK);
		Mockito.when(accountServices.transferRequest(transferBalanceRequest)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.fundTransferRequest(transferBalanceRequest);

		// outcome
		assertEquals(responseEntity, result);
	}
	
	@Test
	@DisplayName("fundTransfer Request Function: negitive Scenario")
	public void fundTransferRequest1() {
		// given or context
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", Status.FAILURE);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body,
				HttpStatus.NOT_MODIFIED);
		Mockito.when(accountServices.transferRequest(transferBalanceRequest)).thenReturn(responseEntity);

		// event
		ResponseEntity<Map<String, Object>> result = userController.fundTransferRequest(transferBalanceRequest);

		// outcome
		assertEquals(responseEntity, result);
	}
}
