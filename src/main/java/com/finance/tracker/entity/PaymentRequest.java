package com.finance.tracker.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String reason;
	private Long requestToUser;
	private Long requestFromUser;
	private Double ammount;
	private Boolean paid;
//	private Transection transection;
	private Date requestDate;
	private Date paidDate;

//	@OneToOne(mappedBy = "paymentRequest")
//	private SplitBetween splitBetween;
}
