package com.finance.tracker.dto;

import com.finance.tracker.config.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitBillViewDto {
	private Long billId;
	private User splitingUser;
}
