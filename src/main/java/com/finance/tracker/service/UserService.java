package com.finance.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finance.tracker.config.User;
import com.finance.tracker.config.security.JwtRequest;
import com.finance.tracker.dto.UserDto;

@Service
public interface UserService {

	// for saving the user
	public UserDto saveUser(UserDto userDto);

	// for get users
	public List<UserDto> getAllUsers();

	// update user
	public UserDto updateUser(UserDto userDto);

	/*
	 * while adding email check for uniqueness
	 */

	public boolean getUniqueEmail(String email);

	/*
	 * while adding password check for uniqueness
	 */
	public boolean getUniquePassword(String password);

	/*
	 * while adding moblilenumber check for uniqueness
	 */

	public boolean getUniqueMobileNumber(String mobileNumber);

	/*
	 * while updating email check for uniqueness
	 */
	public boolean getUniqueUser(Long id, String email);

	/*
	 * while updating mobile number check for uniqueness
	 */
	public boolean getUniqueUserByPhone(Long id, String mobileNumber);

	/*
	 * while updating password check for uniqueness
	 */
	public boolean getUniquePassword(Long id, String password);
	
	public Optional<User> checkLoginUser(JwtRequest jwtRequest);

}
//public String registerUser(UserDto userDto);

//for delete the user
//public void deleteUserById(Long id);

//get user
//public UserDto getUser(Long id);
