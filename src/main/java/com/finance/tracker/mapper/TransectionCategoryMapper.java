package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;

import com.finance.tracker.dto.TransectionCategoryDto;
import com.finance.tracker.entity.TransectionCategory;

public class TransectionCategoryMapper {

	public static TransectionCategoryDto transectionCategoryToTransectionCategoryDto(TransectionCategory transectionCategory)
	{
		TransectionCategoryDto transectionCategoryDto = new TransectionCategoryDto();
		BeanUtils.copyProperties(transectionCategory, transectionCategoryDto);
		return transectionCategoryDto;
	}
	
	public static TransectionCategory transectionCategoryDtoToTransectionCategory(TransectionCategoryDto transectionCategoryDto)
	{
		TransectionCategory transectionCategory = new TransectionCategory();
		BeanUtils.copyProperties(transectionCategoryDto, transectionCategory);
		return transectionCategory;
	}
}
