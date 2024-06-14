package com.finance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.tracker.dto.TransectionTypeDto;
import com.finance.tracker.entity.TransectionType;
import com.finance.tracker.mapper.TransectionMapper;
import com.finance.tracker.mapper.TransectionTypeMapper;
import com.finance.tracker.repo.TransectionTypeRepository;
import com.finance.tracker.service.TransectionTypeService;

@Service
public class TransectionTypeServiceImpl implements TransectionTypeService{

	@Autowired
	TransectionTypeRepository transectionTypeRepository;

	@Override
	public TransectionTypeDto saveTransectionType(TransectionTypeDto transectionTypeDto) {
		TransectionType transectionType = TransectionTypeMapper.transectionTypeDtoToTransectionType(transectionTypeDto);
		transectionTypeRepository.save(transectionType);
		transectionTypeDto = TransectionTypeMapper.transectionTypeToTransectionTypeDto(transectionType);
		return transectionTypeDto;
	}
}
