package com.finance.tracker.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.tracker.dto.SplitBetweenDto;
import com.finance.tracker.dto.SplitBillDto;
import com.finance.tracker.dto.SplitBillWithAllDto;
import com.finance.tracker.entity.SplitBetween;
import com.finance.tracker.entity.SplitBill;
import com.finance.tracker.repo.SplitBetweenRepository;

@Component
public class SplitBillMapper {

	@Autowired
	SplitBetweenRepository splitBetweenRepository;

	@Autowired
	SplitBetweenMapper splitBetweenMapper;

	public SplitBillDto splitBillToSplitBillDto(SplitBill splitBill) {
		SplitBillDto splitBillDto = new SplitBillDto();
		BeanUtils.copyProperties(splitBill, splitBillDto);
		return splitBillDto;
	}

	public SplitBill splitBillDtoToSplitBill(SplitBillDto splitBillDto) {
		SplitBill splitBill = new SplitBill();
		BeanUtils.copyProperties(splitBillDto, splitBill);
		return splitBill;
	}

	public SplitBillWithAllDto splitBillToSplitBillWithAllDto(SplitBill splitBill) {
		SplitBillWithAllDto splitBillWithAllDto = new SplitBillWithAllDto();
		BeanUtils.copyProperties(splitBill, splitBillWithAllDto);
		List<SplitBetween> splitBetweens = splitBetweenRepository.findBySplitBillId(splitBill.getBillId());
		List<SplitBetweenDto> splitBetweenDtos = new ArrayList<>();

		for (SplitBetween splitBetween : splitBetweens) {
			splitBetweenDtos.add(splitBetweenMapper.splitBetweenToSplitBetweenDto(splitBetween));
		}

		splitBillWithAllDto.setSplitBetweenDtos(splitBetweenDtos);
		return splitBillWithAllDto;
	}
}
