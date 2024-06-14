package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;

import com.finance.tracker.dto.TransectionDto;
import com.finance.tracker.dto.TransectionTypeDto;
import com.finance.tracker.entity.Transection;
import com.finance.tracker.entity.TransectionType;

public class TransectionTypeMapper {

	public static TransectionTypeDto transectionTypeToTransectionTypeDto(TransectionType transectionType)
	{
		TransectionTypeDto transectionTypeDto = new TransectionTypeDto();
		BeanUtils.copyProperties(transectionType, transectionTypeDto);
		return transectionTypeDto;
	}
	
	public static TransectionType transectionTypeDtoToTransectionType(TransectionTypeDto transectionTypeDto)
	{
		TransectionType transectionType = new TransectionType();
		BeanUtils.copyProperties(transectionTypeDto, transectionType);
		return transectionType;
	}
}
