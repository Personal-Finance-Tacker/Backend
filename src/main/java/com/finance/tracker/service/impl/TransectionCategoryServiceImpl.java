package com.finance.tracker.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.tracker.dto.TransectionCategoryDto;
import com.finance.tracker.entity.TransectionCategory;
import com.finance.tracker.mapper.TransectionCategoryMapper;
import com.finance.tracker.repo.TransectionCategoryRepository;
import com.finance.tracker.service.TransectionCategoryService;

@Service
public class TransectionCategoryServiceImpl implements TransectionCategoryService{
	
	@Autowired
	TransectionCategoryRepository transectionCategoryRepository;

	@Override
	public TransectionCategoryDto saveTransectionCategoryDto(TransectionCategoryDto transectionCategoryDto) {
		TransectionCategory transectionCategory = TransectionCategoryMapper.transectionCategoryDtoToTransectionCategory(transectionCategoryDto);
		transectionCategoryRepository.save(transectionCategory);
		transectionCategoryDto = TransectionCategoryMapper.transectionCategoryToTransectionCategoryDto(transectionCategory);
		return transectionCategoryDto;
	}
	
	@Override
	public List<TransectionCategoryDto> getAllTransectionCategory() {
		List <TransectionCategory> transectionCategorys = transectionCategoryRepository.findAll();
		List<TransectionCategoryDto> transectionCategoryDtos=new ArrayList<>();
		for (Iterator cateoryIterator = transectionCategorys.iterator(); cateoryIterator.hasNext();) {
			TransectionCategoryDto transectionCategoryDto2=new TransectionCategoryDto();
			BeanUtils.copyProperties(cateoryIterator.next(), transectionCategoryDto2);
			transectionCategoryDtos.add(transectionCategoryDto2);			
		}

		return transectionCategoryDtos;
	}

	
}