package com.finance.tracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
	public List<PaymentRequest> findAllByRequestToUser(Long id);

	public List<PaymentRequest> findAllByRequestFromUser(Long id);
}
