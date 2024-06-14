package com.finance.tracker.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.tracker.dto.UserDto;
import com.finance.tracker.config.User;
import com.finance.tracker.config.UserRole;
import com.finance.tracker.config.security.JwtRequest;
import com.finance.tracker.mapper.UserMapper;
import com.finance.tracker.repo.RoleRepository;
import com.finance.tracker.repo.UserRepository;
import com.finance.tracker.repo.UserRoleRepository;
import com.finance.tracker.service.UserService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;

	// for saving the user
	@Override
	public UserDto saveUser(UserDto userDto) {

		// convert userDto to user
		User user = userMapper.userDtoToUser(userDto);
		user.setUserWallet(0.0);
		// save user
		user=userRepository.save(user);
		
		//set User Role for first time only
		HashSet<UserRole> userRoles = new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(roleRepository.findById(1l).get());
		userRoles.add(userRoleRepository.save(userRole));
		user.setUserRoles(userRoles);
		
//		// convert user to userDto
		userDto = userMapper.userToUserDto(user);
		// return userdto
		return userDto;
	}
	
	@Override
	public Optional<User> checkLoginUser(JwtRequest jwtRequest) {
		return userRepository.findByPasswordAndEmailIgnoreCase(jwtRequest.getPassword(),jwtRequest.getUsername());
	}

	// get all users

	@Override
	public List<UserDto> getAllUsers() {

		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();

		for (User user : userList) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userDtoList.add(userDto);
		}

		return userDtoList;
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setId(userDto.getId());
		userRepository.save(user);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public boolean getUniqueUser(Long id, String email) {
		List<User> users = userRepository.findByIdIsNotAndEmailIgnoreCase(id, email);
		System.out.println(users);
		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getUniqueUserByPhone(Long id, String mobileNumber) {
		List<User> users = userRepository.findByIdIsNotAndMobileNumber(id, mobileNumber);
		System.out.println(users);
		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getUniquePassword(String password) {
		List<User> users = userRepository.findByPassword(password);

		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getUniquePassword(Long id, String password) {
		List<User> users = userRepository.findByIdIsNotAndPassword(id, password);
		System.out.println(users);
		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getUniqueEmail(String email) {
		List<User> users = userRepository.findByEmail(email);

		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getUniqueMobileNumber(String mobileNumber) {

		List<User> users = userRepository.findByMobileNumber(mobileNumber);

		if (!users.isEmpty()) {
			return true;
		}
		return false;
	}

}

//String phone_regex = "^\\d{10}$";
//String pass_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,}$";
//String email_regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

//@Override
//public UserDto getUser(Long id) {
//	UserDto userDto = new UserDto();
//	User user = userRepository.findById(id).get();
//	BeanUtils.copyProperties(user, userDto);
//	return userDto;
//}

// for delete the user
//@Override
//public void deleteUserById(Long id) {
//	userRepository.deleteById(id);
//}

//public String registerUser(UserDto userDto) {
//
//User user = UserMapper.userDtoToUser(userDto);
////Optional<User> userName = userRepository.findByName(user.getName());
//Optional<User> mobilenumber = userRepository.findByMobileNumber(user.getMobileNumber());
//Optional<User> email = userRepository.findByEmail(user.getEmail());
//
//if (mobilenumber.isPresent() || email.isPresent()) {
//	return "registration failed!!!!";
//}else {
//	return "registration success!!!!";
//}
//}
