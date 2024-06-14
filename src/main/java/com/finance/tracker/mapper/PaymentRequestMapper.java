package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.finance.tracker.dto.PaymentRequestDto;
import com.finance.tracker.entity.PaymentRequest;

@Component
public class PaymentRequestMapper {

	public PaymentRequest paymentRequestDtoToPaymentRequest(PaymentRequestDto paymentRequestDto) {
		PaymentRequest paymentRequest = new PaymentRequest();
		BeanUtils.copyProperties(paymentRequestDto, paymentRequest);
		return paymentRequest;
	}

	public PaymentRequestDto paymentRequestToPaymentRequestDto(PaymentRequest paymentRequest) {
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
		BeanUtils.copyProperties(paymentRequest, paymentRequestDto);
		return paymentRequestDto;
	}
}
