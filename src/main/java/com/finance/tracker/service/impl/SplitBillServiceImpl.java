package com.finance.tracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.tracker.config.User;
import com.finance.tracker.dto.SplitBillDto;
import com.finance.tracker.dto.SplitBillWithAllDto;
import com.finance.tracker.entity.PaymentRequest;
import com.finance.tracker.entity.SplitBetween;
import com.finance.tracker.entity.SplitBill;
import com.finance.tracker.entity.Transection;
import com.finance.tracker.exception.NotFoundException;
import com.finance.tracker.mapper.SplitBillMapper;
import com.finance.tracker.pagination.Pagination;
import com.finance.tracker.repo.PaymentRequestRepository;
import com.finance.tracker.repo.SplitBetweenRepository;
import com.finance.tracker.repo.SplitBillRepository;
import com.finance.tracker.repo.TransectionCategoryRepository;
import com.finance.tracker.repo.TransectionRepository;
import com.finance.tracker.repo.TransectionTypeRepository;
import com.finance.tracker.repo.UserRepository;
import com.finance.tracker.service.SplitBillService;

@Service
public class SplitBillServiceImpl implements SplitBillService {
	@Autowired
	SplitBillMapper splitBillMapper;

	@Autowired
	SplitBillRepository splitBillRepository;

	@Autowired
	SplitBetweenRepository splitBetweenRepository;

	@Autowired
	PaymentRequestRepository paymentRequestRepository;

	@Autowired
	UserRepository userDao;

	@Autowired
	TransectionRepository transectionRepository;

	@Autowired
	TransectionTypeRepository transectionTypeRepository;

	@Autowired
	TransectionCategoryRepository tansectionCategoryRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	@Override
	public void saveSplitBill(SplitBillDto splitBillDto) {
		SplitBill splitBill = new SplitBill();
		splitBill.setPaid(false);
		splitBill.setSplitingUser(userDao.findById(splitBillDto.getSplitedByUserId()).get());
		Double totalAmount = splitBillDto.getTotalAmount();
		splitBill.setTotalBill(totalAmount);
		splitBill.setSpiltDescription(splitBillDto.getSpiltDescription());
		Double amountPayToIndividule = totalAmount / (splitBillDto.getSplitedBetween().size() + 1);
		splitBill.setAmountPayToIndevidual(amountPayToIndividule);
		splitBill.setSplitDate(new Date(System.currentTimeMillis()));

		Transection transection = new Transection();
		transection.setTransectionAmount(totalAmount);
		transection.setTransectionTitle("Split bill: "+splitBillDto.getSpiltDescription());
		transection.setTransectionType(transectionTypeRepository.findById(2l).get());
		transection.setTransectionCategory(tansectionCategoryRepository.findById(1l).get());
		transection.setTransectionDate(new Date(System.currentTimeMillis()));
		User user = userDao.findById(splitBillDto.getSplitedByUserId()).get();

		Double amount = user.getUserWallet();
		user.setUserWallet(amount - totalAmount);
		userDao.save(user);
		transection.setUser(user);
		transection = transectionRepository.save(transection);

		splitBill.setTransection(transection);
		SplitBill splitBill2 = splitBillRepository.save(splitBill);
		saveSplitBetween(splitBill2, splitBillDto.getSplitedBetween());
	}

	@Transactional
	@Override
	public void saveSplitBetween(SplitBill splitBill, Set<String> splitBetweenUsers) {
		for (String email : splitBetweenUsers) {
			SplitBetween splitBetween = new SplitBetween();
			User userOptional = userDao.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User not Found"));
			System.out.println(email);
			Long id = userOptional.getId();
			splitBetween.setPaid(false);
			splitBetween.setSplitBillUserId(id);
			splitBetween.setSplitBillId(splitBill.getBillId());

			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setAmmount(splitBill.getAmountPayToIndevidual());
			paymentRequest.setReason(splitBill.getSpiltDescription());
			paymentRequest.setPaid(false);
			paymentRequest.setRequestFromUser(splitBill.getSplitingUser().getId());
			paymentRequest.setRequestToUser(id);
			paymentRequest.setRequestDate(new Date(System.currentTimeMillis()));

			paymentRequest = paymentRequestRepository.save(paymentRequest);

			splitBetween.setPaymentRequest(paymentRequest);
			splitBetweenRepository.save(splitBetween);
		}
	}

	@Override
	public void updateSplitBill(Long id, SplitBillDto splitBillDto) {

		SplitBill splitBill = splitBillRepository.findById(id).orElseThrow(() -> new NotFoundException("This split bill id does not exsist."));

		splitBill.setPaid(false);
		User user1 = userDao.findById(splitBillDto.getSplitedByUserId()).orElseThrow(() -> new NotFoundException("user does not exists."));
		System.out.println(user1);
		splitBill.setSplitingUser(user1);
		Double totalAmount = splitBillDto.getTotalAmount();
		splitBill.setTotalBill(totalAmount);
		Double amountPayToIndividule = totalAmount / (splitBillDto.getSplitedBetween().size() + 1);
		splitBill.setAmountPayToIndevidual(amountPayToIndividule);

		Transection transection = splitBill.getTransection();

		transection.setTransectionTitle("Split bill");
		transection.setTransectionType(transectionTypeRepository.findById(2l).get());
		transection.setTransectionCategory(tansectionCategoryRepository.findById(1l).get());
		transection.setTransectionDate(new Date(System.currentTimeMillis()));
		transection.setUser(userDao.findById(splitBillDto.getSplitedByUserId()).get());
		User user = userDao.findById(splitBillDto.getSplitedByUserId()).get();

		Double amount = user.getUserWallet();
		user.setUserWallet(amount + transection.getTransectionAmount() - totalAmount);
		userDao.save(user);
		transection.setUser(user);

		transection.setTransectionAmount(totalAmount);
		transection = transectionRepository.save(transection);

		splitBill.setTransection(transection);
		SplitBill splitBill2 = splitBillRepository.save(splitBill);
		updateSplitBetween(splitBill2, splitBillDto.getSplitedBetween());
	}

	public void updateSplitBetween(SplitBill splitBill, Set<String> splitBetweenUsers) {
		Long billId = splitBill.getBillId();
		List<SplitBetween> listOfSplitBetweens = splitBetweenRepository.findBySplitBillId(billId);

		for (SplitBetween splitBetween : listOfSplitBetweens) {
			PaymentRequest paymentRequest = splitBetween.getPaymentRequest();
			paymentRequestRepository.deleteById(paymentRequest.getId());
			splitBetweenRepository.deleteById(splitBetween.getId());
		}

		saveSplitBetween(splitBill, splitBetweenUsers);

	}

	@Override
	public Pagination getAllSplitBills(Long id, int page, int size) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User does not exists."));
		Pagination pagination = new Pagination();

		Pageable pageable = PageRequest.of(page, size);
		Page<SplitBill> pages = splitBillRepository.findBySplitingUser(user, pageable);
		List<SplitBill> splitBills = pages.getContent();

		List<SplitBillWithAllDto> splitBillDtos = new ArrayList<>();

		for (SplitBill splitBill : splitBills) {
			splitBillDtos.add(splitBillMapper.splitBillToSplitBillWithAllDto(splitBill));
		}

		pagination.setList(splitBillDtos);
		pagination.setCurrentPageNumber(pageable.getPageNumber() + 1);
		pagination.setHasNext(pages.hasNext());
		pagination.setHasPrevious(pageable.hasPrevious());
		pagination.setTotalCount(splitBillDtos.size());
		pagination.setPageSize(pageable.getPageSize());

		return pagination;
	}

	@Override
	public SplitBillWithAllDto getSplitBill(Long id) {
		System.out.println(id);
		SplitBill splitBill = splitBillRepository.findByBillId(id).orElseThrow(() -> new NotFoundException("Split, Bill id does not exists."));
		return splitBillMapper.splitBillToSplitBillWithAllDto(splitBill);
	}

	@Override
	public void deleteSplitBill(Long id) {
		SplitBill splitBill = splitBillRepository.findByBillId(id).orElseThrow(() -> new NotFoundException("Split Bill id does not exists."));
		List<SplitBetween> listOfSplitBetweens = splitBetweenRepository.findBySplitBillId(id);

		for (SplitBetween splitBetween : listOfSplitBetweens) {
			if (splitBetween.getPaid() == true) {
				User user = userDao.findById(splitBetween.getSplitBillUserId()).orElseThrow(() -> new NotFoundException("User id does not exists."));
				user.setUserWallet(splitBill.getAmountPayToIndevidual());
				userDao.save(user);

				Transection transection = new Transection();
				transection.setTransectionAmount(splitBill.getAmountPayToIndevidual());
				transection.setTransectionCategory(
						tansectionCategoryRepository.findById(1l).orElseThrow(() -> new NotFoundException("Transaction Category id does not found.")));
				transection.setTransectionDate(new Date(System.currentTimeMillis()));
				transection.setTransectionTitle("Refund money because Split bill is deleted.");
				transection.setTransectionType(
						transectionTypeRepository.findById(1l).orElseThrow(() -> new NotFoundException("Transaction type id does not exists.")));
				transection.setUser(user);
				transectionRepository.save(transection);
			}

			paymentRequestRepository.deleteById(splitBetween.getPaymentRequest().getId());
			splitBetweenRepository.deleteById(splitBetween.getId());
		}

		User user = splitBill.getSplitingUser();
		Transection transection = new Transection();
		transection.setTransectionAmount(splitBill.getTotalBill());
		transection.setTransectionCategory(
				tansectionCategoryRepository.findById(1l).orElseThrow(() -> new NotFoundException("Transaction Category id does not found.")));
		transection.setTransectionDate(new Date(System.currentTimeMillis()));
		transection.setTransectionTitle("Refund money because Split bill is deleted.");
		transection.setTransectionType(transectionTypeRepository.findById(1l).orElseThrow(() -> new NotFoundException("Transaction type id does not exists.")));
		transection.setUser(user);
		transectionRepository.save(transection);

		splitBillRepository.deleteById(splitBill.getBillId());
	}

}
