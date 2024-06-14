package com.finance.tracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRequestService {
	public ResponseEntity<Object> getPaymentRequest(Long id);

	public ResponseEntity<Object> getRequest(Long id);

	public String payToPaymentRequest(Long id);

}
