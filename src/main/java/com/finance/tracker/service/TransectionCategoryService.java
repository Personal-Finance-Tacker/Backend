package com.finance.tracker.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finance.tracker.dto.TransectionCategoryDto;
import com.finance.tracker.entity.TransectionCategory;

@Service
public interface TransectionCategoryService {

	public TransectionCategoryDto saveTransectionCategoryDto(TransectionCategoryDto transectionCategoryDto);

	List<TransectionCategoryDto> getAllTransectionCategory();
}
