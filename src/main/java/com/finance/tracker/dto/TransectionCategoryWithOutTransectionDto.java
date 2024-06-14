package com.finance.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransectionCategoryWithOutTransectionDto {

    private Long categoryId;
	
	private String categoryName;
}
