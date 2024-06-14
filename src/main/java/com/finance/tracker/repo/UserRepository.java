package com.finance.tracker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.tracker.config.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * while adding email check for uniqueness
	 */

	public List<User> findByEmail(String email);

	/*
	 * while adding password check for uniqueness
	 */

	public List<User> findByPassword(String password);

	/*
	 * while adding mobilenumber check for uniqueness
	 */

	public List<User> findByMobileNumber(String mobileNumber);

	/*
	 * while updating email check for uniqueness
	 */

	public List<User> findByIdIsNotAndEmailIgnoreCase(Long id, String email);

	/*
	 * while updating password check for uniqueness
	 */

	public List<User> findByIdIsNotAndPassword(Long id, String password);

	/*
	 * while updating mobilenumber check for uniqueness
	 */

	public List<User> findByIdIsNotAndMobileNumber(Long id, String mobileNumber);

	public Optional<User> findByPasswordAndEmailIgnoreCase(String email, String pass);

	/**
	 * @param  email
	 * @return
	 */
	public Optional<User> findUserByEmail(String email);

}
