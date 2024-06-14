package com.finance.tracker.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finance.tracker.dto.TransectionTypeDto;
import com.finance.tracker.entity.TransectionType;

@Service
public interface TransectionTypeService  {

	public TransectionTypeDto saveTransectionType(TransectionTypeDto transectionTypeDto); 
}
