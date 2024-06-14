package com.finance.tracker.dto;

import java.util.List;

import com.finance.tracker.entity.Transection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransectionTypeDto {

	private Long typeId;

	private String transectionTypeName;

	private List<Transection> transection;

//	@Override
//	public String toString() {
//		return "TransectionTypeDto [typeId=" + typeId + ", transectionTypeName=" + transectionTypeName
//				+  "]";
//	}
	
	
}
