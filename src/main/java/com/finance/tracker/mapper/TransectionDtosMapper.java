package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;

import com.finance.tracker.dto.TransectionDtos;
import com.finance.tracker.entity.Transection;

public class TransectionDtosMapper {

	public static TransectionDtos transectionToTransectionDtos(Transection transection)
	{
		TransectionDtos transectionDtos = new TransectionDtos();
		BeanUtils.copyProperties(transection, transectionDtos);
		return transectionDtos;
	}
	
	public static Transection transectionDtosToTransection(TransectionDtos transectionDtos)
	{
		Transection transection = new Transection();
		BeanUtils.copyProperties(transectionDtos, transection);
		return transection;
	}
}
