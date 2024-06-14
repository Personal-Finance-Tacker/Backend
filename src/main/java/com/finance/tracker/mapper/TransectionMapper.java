package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;

import com.finance.tracker.dto.TransectionDto;
import com.finance.tracker.entity.Transection;

public class TransectionMapper {

	public static TransectionDto transectionToTransectionDto(Transection transection) {
		TransectionDto transectionDto = new TransectionDto();
		BeanUtils.copyProperties(transection, transectionDto);
		transectionDto.setCategoryId(transection.getTransectionCategory().getCategoryId());
		transectionDto.setTypeId(transection.getTransectionType().getTypeId());
		transectionDto.setUserId(transection.getUser().getId());
		return transectionDto;
	}

	public static Transection transectionDtoToTransection(TransectionDto transectionDto) {
		Transection transection = new Transection();
		BeanUtils.copyProperties(transectionDto, transection);
		return transection;
	}
}
