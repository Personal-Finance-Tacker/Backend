package com.finance.tracker.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.finance.tracker.dto.SplitBillDto;
import com.finance.tracker.dto.SplitBillWithAllDto;
import com.finance.tracker.entity.SplitBill;
import com.finance.tracker.pagination.Pagination;

@Service
public interface SplitBillService {
	public void saveSplitBill(SplitBillDto splitBillDto);

	public void saveSplitBetween(SplitBill splitBillId, Set<String> splitBetweenUsers);

	public void updateSplitBill(Long id, SplitBillDto splitBillDto);

	public Pagination getAllSplitBills(Long id, int page, int size);

	public SplitBillWithAllDto getSplitBill(Long id);

	public void deleteSplitBill(Long id);
}
