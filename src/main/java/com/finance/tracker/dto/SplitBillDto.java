package com.finance.tracker.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitBillDto {
	private Long splitedByUserId;
	private Double totalAmount;
	private String spiltDescription;
	private Long transectionTypeId;
	private Long categoryId;
	private Set<String> splitedBetween;

}
