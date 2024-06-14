package com.finance.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitBetweenDto {
	private Long id;

	private Long splitBillId;

	private String userEmail;

	private Boolean paid;
}
