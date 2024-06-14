package com.finance.tracker.entity;

import java.util.Date;

import com.finance.tracker.config.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billId;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private User splitingUser;

	private Double totalBill;
	
	private String spiltDescription;

	private Boolean paid;

//	@OneToMany(mappedBy = "splitBillId")
//	private List<SplitBetween> splitedBetween;
	@OneToOne
	private Transection transection;

	private Date splitDate;

	private Double amountPayToIndevidual;
}
