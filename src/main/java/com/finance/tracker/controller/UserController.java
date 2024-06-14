package com.finance.tracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.config.User;
import com.finance.tracker.config.security.JwtRequest;
import com.finance.tracker.config.security.JwtResponse;
import com.finance.tracker.config.security.JwtService;
import com.finance.tracker.dto.UserDto;
import com.finance.tracker.pagination.Pagination;
import com.finance.tracker.service.PaymentRequestService;
import com.finance.tracker.service.SplitBillService;
import com.finance.tracker.service.impl.UserServiceImpl;
import com.finance.tracker.validator.CustomValidator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "perform operation on user")
public class UserController {

	@Autowired
	PaymentRequestService paymentRequestService;

	@Autowired
	SplitBillService splitBillService;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	CustomValidator customValidator;

	@Autowired
	JwtService jwtService;

	@InitBinder
	public void UniqueInitBinder(WebDataBinder binder) {
		if (binder.getTarget() != null && UserDto.class.equals(binder.getTarget().getClass())) {
			binder.addValidators(customValidator);
		}
	}

	// insert(save) the user
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody @Valid UserDto userDto) {
		userServiceImpl.saveUser(userDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.CREATED);

		return new ResponseEntity<>(errorMap, HttpStatus.OK);
	}

	// Login User
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid JwtRequest jwtRequest) {
		Optional<User> user = userServiceImpl.checkLoginUser(jwtRequest);
		if (user.isPresent()) {
			JwtResponse jwtResponse = JwtResponse.builder().token(jwtService.generateToken(user.get())).userId(user.get().getId()).build();
			return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
		} else {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("status", "Worng Credential");
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
	}

	// get users
	@GetMapping("/getusers")
	public List<UserDto> getUsers() {
		List<UserDto> userDtoList = userServiceImpl.getAllUsers();
		return userDtoList;
	}

	// update user
	@PutMapping("/updateuser")
	public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto) {
		UserDto user = userServiceImpl.updateUser(userDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.OK);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// get user
//	 @GetMapping("/getusers/{id}")
//	 public UserDto getUser(@PathVariable Long id )
//	 {
//		UserDto userDto = userServiceImpl.getUser(id);
//		return userDto;
//	 }

	// delete the user
//	 @DeleteMapping("/deleteuser/{id}")
//	 public ResponseEntity<Void> delete(@PathVariable Long id)
//	 {
//		userServiceImpl.deleteUserById(id);
//
//		Map<String, Object> errorMap = new HashMap<>();
//		errorMap.put("status", HttpStatus.OK);
//
//		return new ResponseEntity<>(HttpStatus.OK);
//	 }

	/**
	 * get all payment requests of given user id
	 *
	 * @param  id
	 * @return
	 */
	@GetMapping("/payment/requestes/{id}")
	public ResponseEntity<Object> getPaymentRequest(@PathVariable("id") Long id) {
		return paymentRequestService.getPaymentRequest(id);
	}

	/**
	 * return Payment requests user made to other users.
	 *
	 * @param  id
	 * @param  page
	 * @param  size
	 * @return
	 */
	@GetMapping("/requests/{id}")
	public ResponseEntity<Object> getRequest(@PathVariable("id") Long id) {
		return paymentRequestService.getRequest(id);
	}

	@GetMapping("/splitbills/{id}")
	public Pagination getAllSplitBill(@PathVariable("id") Long id, @RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "5") int size) {
		return splitBillService.getAllSplitBills(id, page, size);
	}
}
