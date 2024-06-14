package com.finance.tracker.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitBetween {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long splitBillId;

	private Long splitBillUserId;

	private Boolean paid;

	@OneToOne(cascade = CascadeType.ALL)
	private PaymentRequest paymentRequest;

}
