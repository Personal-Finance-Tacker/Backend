package com.finance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.service.PaymentRequestService;

@RestController
@RequestMapping("/requests")
public class PaymentRequestController {

	@Autowired
	PaymentRequestService paymentRequestService;

	@PostMapping("/pay/{id}")
	public ResponseEntity<String> payToRequest(@PathVariable("id") Long id) {
		String message = paymentRequestService.payToPaymentRequest(id);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}