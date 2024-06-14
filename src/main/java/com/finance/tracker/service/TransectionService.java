package com.finance.tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finance.tracker.dto.FetchTransectionDto;
import com.finance.tracker.dto.TransectionDto;
import com.finance.tracker.dto.TransectionDtos;

@Service
public interface TransectionService {

	public TransectionDto saveTransection(TransectionDto transectionDto);


	public FetchTransectionDto getTransection(Long id);

	public void deleteTransection(Long id);

	public TransectionDto updateTransction(TransectionDto transectionDto);

	public List<TransectionDtos> getFilteredTransection(Double amount, String transectionCategoryName, String transectionType, String transectionTitle);

	List<TransectionDtos> getTransections(Long userId);
}
