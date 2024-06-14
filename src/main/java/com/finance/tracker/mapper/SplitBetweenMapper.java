package com.finance.tracker.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.tracker.dto.SplitBetweenDto;
import com.finance.tracker.entity.SplitBetween;
import com.finance.tracker.repo.UserRepository;

@Component
public class SplitBetweenMapper {

	@Autowired
	UserRepository userRepository;

	public SplitBetween splitBetweenDtoToSplitBetween(SplitBetweenDto splitBetweenDto) {
		SplitBetween splitBetween = new SplitBetween();
		BeanUtils.copyProperties(splitBetweenDto, splitBetween);
		splitBetween.setSplitBillUserId(userRepository.findByEmail(splitBetweenDto.getUserEmail()).get(0).getId());
		return splitBetween;
	}

	public SplitBetweenDto splitBetweenToSplitBetweenDto(SplitBetween splitBetween) {
		SplitBetweenDto splitBetweenDto = new SplitBetweenDto();
		BeanUtils.copyProperties(splitBetween, splitBetweenDto);
		splitBetweenDto.setUserEmail(userRepository.findById(splitBetween.getSplitBillUserId()).get().getEmail());
		return splitBetweenDto;

	}
}