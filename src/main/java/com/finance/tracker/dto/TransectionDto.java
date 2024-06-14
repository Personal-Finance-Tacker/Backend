package com.finance.tracker.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransectionDto {

	private Long transectionId;

	private String transectionTitle;

	@Min(value = 0)
	@Max(value = 10000000)
	private Double transectionAmount;

	private Long typeId;

	private Long categoryId;

	private Long userId;

	private Date transectionDate;

}
