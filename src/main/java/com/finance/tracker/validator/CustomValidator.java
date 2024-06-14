package com.finance.tracker.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.finance.tracker.dto.UserDto;
import com.finance.tracker.service.impl.UserServiceImpl;

@Component
public class CustomValidator implements Validator {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDto userDto = (UserDto) target;

		if (userDto.getId() != null) {

			if (userServiceImpl.getUniqueUser(userDto.getId(), userDto.getEmail())) {
				errors.rejectValue("email", "error", "Email is already exists");
			}

			if (userServiceImpl.getUniqueUserByPhone(userDto.getId(), userDto.getMobileNumber())) {
				errors.rejectValue("mobileNumber", "error", "Mobile number is already exists");
			}

			if (userServiceImpl.getUniquePassword(userDto.getId(), userDto.getPassword())) {
				errors.rejectValue("password", "error", "Password is already exists");
			}
		} else {
			if (userServiceImpl.getUniqueEmail(userDto.getEmail())) {
				errors.rejectValue("email", "error", "Email is already exists");
			}
			if (userServiceImpl.getUniqueMobileNumber(userDto.getMobileNumber())) {
				errors.rejectValue("mobileNumber", "error", "Mobile number is already exists");
			}
			if (userServiceImpl.getUniquePassword(userDto.getPassword())) {
				errors.rejectValue("password", "error", "Password is already exists");
			}
		}

	}

}
