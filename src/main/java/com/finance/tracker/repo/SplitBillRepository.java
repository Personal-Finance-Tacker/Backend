package com.finance.tracker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.tracker.config.User;
import com.finance.tracker.entity.SplitBill;

@Repository
public interface SplitBillRepository extends JpaRepository<SplitBill, Long> {
	public List<SplitBill> findAllByBillId(Long id);

	public Optional<SplitBill> findByBillId(Long id);

	public Page<SplitBill> findBySplitingUser(User splitingUser, Pageable pageable);
}
