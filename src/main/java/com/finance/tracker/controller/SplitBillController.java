package com.finance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.dto.SplitBillDto;
import com.finance.tracker.service.SplitBillService;
import com.finance.tracker.validator.SplitBillValidation;

import jakarta.validation.Valid;

@RestController
public class SplitBillController {
	@Autowired
	SplitBillValidation splitBillValidation;

	@InitBinder
	void splitBillValidation(WebDataBinder binder) {
		System.out.println("--->");
		binder.addValidators(splitBillValidation);
	}

	@Autowired
	SplitBillService splitBillService;

	@PostMapping("/spli-bill")
	public ResponseEntity<Object> splitBill(@Valid @RequestBody SplitBillDto splitBillDto) {
		splitBillService.saveSplitBill(splitBillDto);
		return ResponseEntity.ok("split successfull.");
	}

	@PutMapping("/splitbill/{id}")
	public Object updateSplitBill(@Valid @PathVariable("id") Long id, @RequestBody SplitBillDto splitBillDto) {
		splitBillService.updateSplitBill(id, splitBillDto);
		return ResponseEntity.ok();
	}

	@GetMapping("/splitbill/{id}")
	public ResponseEntity<Object> getSplitBillById(@PathVariable("id") Long id) {

		return new ResponseEntity<>(splitBillService.getSplitBill(id), HttpStatus.OK);
	}

	@DeleteMapping("splitbill/delete/{id}")
	public String deleteSplitBill(@PathVariable("id") Long id) {
		splitBillService.deleteSplitBill(id);
		return "split bill deleted.";
	}
}
