package com.finance.tracker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part.IgnoreCaseType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finance.tracker.config.User;
import com.finance.tracker.exception.NotFoundException;
import com.finance.tracker.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByEmail(username);
		if(users!=null) {
			if(users.size()==1)
				return users.get(0);
			else
				throw new NotFoundException("more user with same email");
		}
		else {
			throw new NotFoundException("User not found with this email");
		}
	}

}