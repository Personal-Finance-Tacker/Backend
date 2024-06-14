package com.finance.tracker.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transection_Type_table")
public class TransectionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeId;

	private String transectionTypeName; 
	
	@OneToMany(mappedBy = "transectionType",fetch = FetchType.LAZY)
	private List<Transection> transection;
	
}
