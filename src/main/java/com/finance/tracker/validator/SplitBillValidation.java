package com.finance.tracker.validator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.finance.tracker.config.User;
import com.finance.tracker.dto.SplitBillDto;
import com.finance.tracker.repo.UserRepository;

@Component
public class SplitBillValidation implements Validator {

	@Autowired
	UserRepository userDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return SplitBillDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("1");
		ValidationUtils.rejectIfEmpty(errors, "splitedByUserId", "This field cannot be empty.");
		ValidationUtils.rejectIfEmpty(errors, "splitedBetween", "This field cannot be empty.");
		ValidationUtils.rejectIfEmpty(errors, "totalAmount", "Enter total bill.");

		SplitBillDto splitBillDto = (SplitBillDto) target;
		Optional<User> userOptional = userDao.findById(splitBillDto.getSplitedByUserId());
		if (userOptional.isEmpty()) {
			errors.rejectValue("splitedByUserId", "404", "User does not exists.");
		}

		for (String email : splitBillDto.getSplitedBetween()) {
			System.out.println("2");
//			Optional<User> userEmialOptional = userDao.findUserByEmail(email);
//			if (userEmialOptional.isEmpty()) {
//				System.out.println("3" + userEmialOptional.get());
//				errors.rejectValue("splitedBetween", "404", "User does not exists.");
//			}
//			if (userOptional.get().getId() == userEmialOptional.get().getId()) {
//				errors.rejectValue("splitedBetween", "", "User cannot split bill with it self.");
//			}

			List<User> users = userDao.findByEmail(email);
			System.out.println("----------->" + users);
			if (users.isEmpty()) {

				errors.rejectValue("splitedBetween", "404", "User does not exists.");
			} else if (users.get(0).getId() == userOptional.get().getId()) {
				errors.rejectValue("splitedBetween", "400", "User cannot split bill with it self.");
			}

		}

	}

}
