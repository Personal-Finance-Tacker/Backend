package com.finance.tracker.dto;

import java.util.List;

import com.finance.tracker.entity.Transection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransectionCategoryDto {

	private Long categoryId;
	
	private String categoryName;

}
