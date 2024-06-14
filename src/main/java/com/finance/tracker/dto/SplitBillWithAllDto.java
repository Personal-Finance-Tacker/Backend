package com.finance.tracker.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitBillWithAllDto {
	private Long billId;
//	private User splitingUser;

	private Double totalBill;

	private String spiltDescription;

	private Boolean paid;

//	private Transection transection;

	private Date splitDate;
	private List<SplitBetweenDto> splitBetweenDtos;
//	private Double amountPayToIndevidual;
}
