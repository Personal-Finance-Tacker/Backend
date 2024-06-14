package com.finance.tracker.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransectionDtos {

	private Long id;

	private String title;

	private String type;

	private String category;

	private Long date;
	
	private Double amount;
}
