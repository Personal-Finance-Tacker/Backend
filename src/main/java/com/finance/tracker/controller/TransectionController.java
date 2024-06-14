package com.finance.tracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.dto.FetchTransectionDto;
import com.finance.tracker.dto.TransectionCategoryDto;
import com.finance.tracker.dto.TransectionDto;
import com.finance.tracker.dto.TransectionDtos;
import com.finance.tracker.dto.TransectionTypeDto;
import com.finance.tracker.service.impl.TransectionCategoryServiceImpl;
import com.finance.tracker.service.impl.TransectionServiceImpl;
import com.finance.tracker.service.impl.TransectionTypeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transection")
public class TransectionController {

	@Autowired
	TransectionServiceImpl transectionServiceImpl;

	@Autowired
	TransectionCategoryServiceImpl transectionCategoryServiceImpl;

	@Autowired
	TransectionTypeServiceImpl transectionTypeServiceImpl;

	/*
	 * for get the transection
	 */

	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> saveTransection(@RequestBody @Valid TransectionDto transectionDto) {

		transectionServiceImpl.saveTransection(transectionDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.CREATED);

		return new ResponseEntity<>(errorMap, HttpStatus.CREATED);
	}

	/*
	 * for get the transections
	 */
	@GetMapping("/all/{userId}")
	public List<TransectionDtos> getTransections(@PathVariable("userId")Long userId) {
		List<TransectionDtos> transectionDtos = transectionServiceImpl.getTransections(userId);
		System.out.println("transectionDtos--->" + transectionDtos);
		return transectionDtos;
	}

	/*
	 * for get the transections
	 */

	@GetMapping("/trans/{id}")
	public FetchTransectionDto getTransection(@PathVariable("id") Long id) {
		FetchTransectionDto transectionDtos = transectionServiceImpl.getTransection(id);
		System.out.println("transectionDtos--->" + transectionDtos);
		return transectionDtos;
	}

	/*
	 * for delete the transection
	 */

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteTransection(@PathVariable("id") Long id) {
		transectionServiceImpl.deleteTransection(id);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.OK);

		return new ResponseEntity<>(errorMap, HttpStatus.OK);
	}

	/*
	 * for update the transection
	 */

	@PutMapping("/update")
	public ResponseEntity<TransectionDto> updateTransection(@RequestBody @Valid TransectionDto transectionDto) {
		System.out.println(transectionDto);
		transectionDto = transectionServiceImpl.updateTransction(transectionDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.ACCEPTED);
		return new ResponseEntity<>(transectionDto, HttpStatus.ACCEPTED);
	}

	/*
	 * applying filter
	 */

	@GetMapping("/filter")
	public List<TransectionDtos> getFilteredTransection(@RequestParam(value = "transectionAmount") Double transectionAmount,
			@RequestParam(value = "transectionCategoryName") String transectionCategoryName, @RequestParam(value = "transectionType") String transectionType,
			@RequestParam(value = "transectionTitle") String transectionTitle) {
		List<TransectionDtos> transectionDtosList = transectionServiceImpl.getFilteredTransection(transectionAmount, transectionCategoryName, transectionType,
				transectionTitle);
		return transectionDtosList;
	}

	/*
	 * transectiontype
	 */

	@PostMapping("/inserttranstype")
	public ResponseEntity<TransectionTypeDto> saveTransectionType(@RequestBody TransectionTypeDto transectionTypeDto) {
		transectionTypeDto = transectionTypeServiceImpl.saveTransectionType(transectionTypeDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.ACCEPTED);

		return new ResponseEntity<>(transectionTypeDto, HttpStatus.ACCEPTED);
	}

	/*
	 * transectionCategory
	 */
	@PostMapping("/inserttranscategory")
	public ResponseEntity<TransectionCategoryDto> saveTransectionCategory(@RequestBody TransectionCategoryDto transectionCategoryDto) {
		transectionCategoryDto = transectionCategoryServiceImpl.saveTransectionCategoryDto(transectionCategoryDto);

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.ACCEPTED);
		return new ResponseEntity<>(transectionCategoryDto, HttpStatus.ACCEPTED);
	}
	
	/*
	 * fetch All TransectionCategory
	 */
	@GetMapping("/getAllCategory")
	public ResponseEntity<Object> getAllTransectionCategory() {
		return ResponseEntity.status(HttpStatus.OK).body(transectionCategoryServiceImpl.getAllTransectionCategory());
	}
	
}
