package com.finance.tracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.finance.tracker.config.User;
import com.finance.tracker.entity.Transection;

@Repository
public interface TransectionRepository extends JpaRepository<Transection, Long>,JpaSpecificationExecutor<Transection>{

	public List<Transection> findByUser(User user);

}
