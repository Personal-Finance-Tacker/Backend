package com.finance.tracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.finance.tracker.config.User;
import com.finance.tracker.dto.FetchTransectionDto;
import com.finance.tracker.dto.TransectionDto;
import com.finance.tracker.dto.TransectionDtos;
import com.finance.tracker.entity.Transection;
import com.finance.tracker.entity.TransectionCategory;
import com.finance.tracker.entity.TransectionType;
import com.finance.tracker.exception.IdNotFoundException;
import com.finance.tracker.mapper.TransectionMapper;
import com.finance.tracker.repo.TransectionCategoryRepository;
import com.finance.tracker.repo.TransectionRepository;
import com.finance.tracker.repo.TransectionTypeRepository;
import com.finance.tracker.repo.UserRepository;
import com.finance.tracker.service.TransectionService;
import com.finance.tracker.specification.TransectionSpecification;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class TransectionServiceImpl implements TransectionService {

	@Autowired
	TransectionRepository transectionRepository;

	@Autowired
	TransectionTypeRepository transectionTypeRepository;

	@Autowired
	TransectionCategoryRepository transectionCategoryRepository;

	@Autowired
	TransectionSpecification<Transection> transectionSpecification;

	@Autowired
	UserRepository userRepository;

	/*
	 * save transection
	 */

	@Override
	public TransectionDto saveTransection(TransectionDto transectionDto) {

		TransectionCategory transectionCategory = null;
		TransectionType transectionType = null;
		User user = null;
		try {
			transectionCategory = transectionCategoryRepository.findById(transectionDto.getCategoryId())
					.orElseThrow(() -> new IdNotFoundException("Id not found"));

			transectionType = transectionTypeRepository.findById(transectionDto.getTypeId()).orElseThrow(() -> new IdNotFoundException("Id not found"));

			user = userRepository.findById(transectionDto.getUserId()).orElseThrow(() -> new IdNotFoundException("Id not found"));

		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		Transection transection = TransectionMapper.transectionDtoToTransection(transectionDto);
		System.out.println(transection);

		transection.setTransectionCategory(transectionCategory);
		transection.setTransectionType(transectionType);
		transection.setUser(user);

		double amount;

		if ("Expense".equals(transection.getTransectionType())) {
			amount = transection.getUser().getUserWallet() - transection.getTransectionAmount();
		} else {
			amount = transection.getUser().getUserWallet() - transection.getTransectionAmount();
		}

		transection.getUser().setUserWallet(amount);

		transectionRepository.save(transection);

		transectionDto = TransectionMapper.transectionToTransectionDto(transection);
		transectionDto.setTransectionId(transection.getTransectionId());
		return transectionDto;
	}

	/*
	 * get transections
	 */

	@Override
	public List<TransectionDtos> getTransections(Long userId) {

		List<TransectionDtos> transectionDtoList = new ArrayList<>();
		List<Transection> transectionList = transectionRepository.findByUser(new User(userId, null, null, 0, null, null, null, null, null));

		for (Transection transection : transectionList) {

			TransectionDtos transectionDtos = new TransectionDtos();
			transectionDtos.setId(transection.getTransectionId());
			transectionDtos.setTitle(transection.getTransectionTitle());
			transectionDtos.setAmount(transection.getTransectionAmount());
			transectionDtos.setCategory(transection.getTransectionCategory().getCategoryName());
			transectionDtos.setDate(transection.getTransectionDate().getTime());
			transectionDtos.setType(transection.getTransectionType().getTransectionTypeName().toLowerCase());
			transectionDtoList.add(transectionDtos);
		}
		return transectionDtoList;
	}

	/*
	 * delete transection
	 */

	@Override
	public void deleteTransection(Long id) {

		Optional<Transection> transection = transectionRepository.findById(id);

		if (transection.isEmpty()) {
			throw new RuntimeException("id not found");
		} else {
			transectionRepository.deleteById(id);
		}
	}

	/*
	 * update transection
	 */

	@Override
	public TransectionDto updateTransction(TransectionDto transectionDto) {
		try {
			Transection transections = transectionRepository.findById(transectionDto.getTransectionId())
					.orElseThrow(() -> new IdNotFoundException("id not found"));
			transections = TransectionMapper.transectionDtoToTransection(transectionDto);
			transections.setTransectionTitle(transectionDto.getTransectionTitle());
			transections.setTransectionAmount(transectionDto.getTransectionAmount());
			transections.setTransectionDate(new Date(System.currentTimeMillis()));
			transections.setTransectionCategory(
					transectionCategoryRepository.findById(transectionDto.getCategoryId()).orElseThrow(() -> new IdNotFoundException("id not found")));

			transections.setTransectionType(
					transectionTypeRepository.findById(transectionDto.getTypeId()).orElseThrow(() -> new IdNotFoundException("id not found")));

			transections.setUser(userRepository.findById(transectionDto.getUserId()).orElseThrow(() -> new IdNotFoundException("id not found")));
			System.out.println("DTO:" + transectionDto + "\nentity:" + transections);
			transectionRepository.save(transections);

			transectionDto = TransectionMapper.transectionToTransectionDto(transections);

		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transectionDto;

	}

	/*
	 * applying filter
	 */

	@Override
	public List<TransectionDtos> getFilteredTransection(Double amount, String transectionCategoryName, String transectionType, String transectionTitle) {

		Specification<Transection> specification = Specification.where(null);

		if (amount != null) {
			specification = specification.and(transectionSpecification.filterByAmount(amount));
		}
		if (transectionCategoryName != null) {
			specification = specification.and(transectionSpecification.filterByTransectionCategoryName(transectionCategoryName));
		}
		if (transectionType != null) {
			specification = specification.and(transectionSpecification.filterByTransectionType(transectionType));
		}
		if (transectionTitle != null) {
			specification = specification.and(transectionSpecification.filterByTransectionTitle(transectionTitle));
		}
		List<Transection> transectionList = transectionRepository.findAll(specification);
		List<TransectionDtos> transectionDtosList = new ArrayList<>();

		for (Transection transection : transectionList) {

			TransectionDtos transectionDtos = new TransectionDtos();
			transectionDtos.setId(transection.getTransectionId());
			transectionDtos.setTitle(transection.getTransectionTitle());
			transectionDtos.setAmount(transection.getTransectionAmount());
			transectionDtos.setCategory(transection.getTransectionCategory().getCategoryName());
			transectionDtos.setDate(transection.getTransectionDate().getTime());
			transectionDtos.setType(transection.getTransectionType().getTransectionTypeName());

			transectionDtosList.add(transectionDtos);
		}
		return transectionDtosList;
	}

	/*
	 * get Transection
	 */
	@Override
	public FetchTransectionDto getTransection(Long id) {

		FetchTransectionDto transectionDtos = new FetchTransectionDto();
		try {
			Transection transection = transectionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));

			transectionDtos.setId(transection.getTransectionId());
			transectionDtos.setTitle(transection.getTransectionTitle());
			transectionDtos.setAmount(transection.getTransectionAmount());
			transectionDtos.setCategory(transection.getTransectionCategory().getCategoryId());
			transectionDtos.setDate(transection.getTransectionDate().getTime());
			transectionDtos.setType(transection.getTransectionType().getTypeId());

		} catch (IdNotFoundException e) {

			e.printStackTrace();
		}
		return transectionDtos;
	}

}
