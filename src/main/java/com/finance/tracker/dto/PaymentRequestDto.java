package com.finance.tracker.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
	private Long id;
	private String reason;
	private Long requestToUser;
	private Long requestFromUser;
	private Double ammount;
	private Boolean paid;
	private Date requestDate;
	private Date paidDate;
}
