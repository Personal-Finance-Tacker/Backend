package com.finance.tracker.config.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.finance.tracker.config.User;
import com.finance.tracker.exception.NotFoundException;
import com.finance.tracker.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;

	private final AuthenticationManager authenticationManager;

	public User authenticate(JwtRequest input) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		List<User> users=userRepository.findByEmail(input.getUsername());
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