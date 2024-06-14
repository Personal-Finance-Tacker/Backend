package com.finance.tracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.PaymentRequest;
import com.finance.tracker.entity.SplitBetween;

public interface SplitBetweenRepository extends JpaRepository<SplitBetween, Long> {
	public List<SplitBetween> findBySplitBillId(Long splitBillId);

//	public List<SplitBetween> findBySplitBillId(Long splitBillId);
	public SplitBetween findByPaymentRequest(PaymentRequest paymentRequest);
}
