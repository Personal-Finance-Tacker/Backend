package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.finance.tracker.dto.UserDto;
import com.finance.tracker.config.User;

@Component
public class UserMapper {

	public UserDto userToUserDto(User user)
	{
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}
	
	public User userDtoToUser(UserDto userDto)
	{
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
}
