package com.finance.tracker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.tracker.config.Role;
import com.finance.tracker.config.User;
import com.finance.tracker.config.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
