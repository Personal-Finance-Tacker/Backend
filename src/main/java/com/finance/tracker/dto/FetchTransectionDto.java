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
public class FetchTransectionDto {

	private Long id;

	private String title;

	private Long type;

	private Long category;

	private Long date;
	
	private Double amount;

}
