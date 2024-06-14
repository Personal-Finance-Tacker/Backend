package com.finance.tracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finance.tracker.config.User;
import com.finance.tracker.dto.PaymentRequestDto;
import com.finance.tracker.entity.PaymentRequest;
import com.finance.tracker.entity.SplitBetween;
import com.finance.tracker.entity.SplitBill;
import com.finance.tracker.entity.Transection;
import com.finance.tracker.exception.NotFoundException;
import com.finance.tracker.mapper.PaymentRequestMapper;
import com.finance.tracker.pagination.Pagination;
import com.finance.tracker.repo.PaymentRequestRepository;
import com.finance.tracker.repo.SplitBetweenRepository;
import com.finance.tracker.repo.SplitBillRepository;
import com.finance.tracker.repo.TransectionCategoryRepository;
import com.finance.tracker.repo.TransectionRepository;
import com.finance.tracker.repo.TransectionTypeRepository;
import com.finance.tracker.repo.UserRepository;
import com.finance.tracker.service.PaymentRequestService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PaymentRequestServiceImpl implements PaymentRequestService {

	@Autowired
	PaymentRequestRepository paymentRequestRepository;

	@Autowired
	PaymentRequestMapper paymentRequestMapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransectionCategoryRepository transectionCategoryRepository;

	@Autowired
	TransectionTypeRepository transectionTypeRepository;

	@Autowired
	TransectionRepository transectionRepository;

	@Autowired
	SplitBetweenRepository splitBetweenRepository;

	@Autowired
	SplitBillRepository splitBillRepository;

	/**
	 * get all(paid and pending) payment requests of user by its id.
	 */
	@Override
	public ResponseEntity<Object> getPaymentRequest(Long id) {
		Pagination pagination = new Pagination();

		List<PaymentRequest> requests = paymentRequestRepository.findAllByRequestToUser(id);
		List<PaymentRequestDto> paymentRequestDtos = new ArrayList<>();
		for (PaymentRequest paymentRequest : requests) {
			if (paymentRequest.getPaid() == false) {
				paymentRequestDtos.add(paymentRequestMapper.paymentRequestToPaymentRequestDto(paymentRequest));
			}
		}

		return new ResponseEntity<>(paymentRequestDtos, HttpStatus.OK);
	}

	/**
	 * Pay requested payment
	 */
	@Override
	public String payToPaymentRequest(Long id) {

		PaymentRequest paymentRequest = paymentRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("This payment request does not exists."));
		User requestToUser = userRepository.findById(paymentRequest.getRequestToUser()).get();
		User requestFromUser = userRepository.findById(paymentRequest.getRequestFromUser()).get();

		Double amount = paymentRequest.getAmmount();

		Transection transection1 = new Transection();
		transection1.setTransectionAmount(amount);
		transection1.setTransectionCategory(transectionCategoryRepository.findById(1l).get());
		transection1.setTransectionType(transectionTypeRepository.findById(2l).get());
		transection1.setTransectionAmount(amount);
		transection1.setTransectionDate(new Date(System.currentTimeMillis()));
		transection1.setTransectionTitle("requested payment");
		transection1.setUser(requestToUser);
		transectionRepository.save(transection1);

		requestToUser.setUserWallet(requestToUser.getUserWallet() - amount);
		userRepository.save(requestToUser);

		Transection transection2 = new Transection();
		transection2.setTransectionCategory(transectionCategoryRepository.findById(1l).get());
		transection2.setTransectionType(transectionTypeRepository.findById(1l).get());
		transection2.setTransectionAmount(amount);
		transection2.setTransectionDate(new Date(System.currentTimeMillis()));
		transection2.setTransectionTitle("requested payment");
		transection2.setUser(requestFromUser);
		transectionRepository.save(transection2);

		requestFromUser.setUserWallet(requestFromUser.getUserWallet() + amount);
		userRepository.save(requestFromUser);

		SplitBetween splitBetween = splitBetweenRepository.findByPaymentRequest(paymentRequestRepository.findById(id).get());
		splitBetween.setPaid(true);
		splitBetweenRepository.save(splitBetween);

		boolean flag = false;
		SplitBill splitBill = splitBillRepository.findByBillId(splitBetween.getSplitBillId())
				.orElseThrow(() -> new NotFoundException("Split Bill id does not exists."));
		List<SplitBetween> splitBetweens = splitBetweenRepository.findBySplitBillId(splitBill.getBillId());
		for (SplitBetween s : splitBetweens) {
			if (s.getPaid().equals(true)) {
				flag = true;
			} else {
				flag = false;
			}
		}

		if (flag) {
			splitBill.setPaid(true);
			splitBillRepository.save(splitBill);
		}

		paymentRequest.setPaidDate(new Date(System.currentTimeMillis()));
		paymentRequest.setPaid(true);
		return "Requested payment is done.";
	}

	@Override
	public ResponseEntity<Object> getRequest(Long id) {
		Pagination pagination = new Pagination();

//		Pageable pageable = PageRequest.of(0, );
//		Page<PaymentRequest> pages = paymentRequestRepository.findAllByRequestFromUser(id);

		List<PaymentRequest> requests = paymentRequestRepository.findAllByRequestFromUser(id);
		List<PaymentRequestDto> paymentRequestDtos = new ArrayList<>();
		for (PaymentRequest paymentRequest : requests) {
			paymentRequestDtos.add(paymentRequestMapper.paymentRequestToPaymentRequestDto(paymentRequest));
		}

//		pagination.setList(paymentRequestDtos);
//		pagination.setCurrentPageNumber(pageable.getPageNumber() + 1);
//		pagination.setHasNext(pages.hasNext());
//		pagination.setHasPrevious(pageable.hasPrevious());
//		pagination.setTotalCount(paymentRequestDtos.size());
//		pagination.setPageSize(pageable.getPageSize());

		return new ResponseEntity<>(paymentRequestDtos, HttpStatus.OK);
	}

}