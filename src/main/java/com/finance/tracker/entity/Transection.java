package com.finance.tracker.entity;

import java.util.Date;

import com.finance.tracker.config.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ToString(exclude = {"transectionType","transectionCategory"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transection_table")
public class Transection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transectionId;

	private String transectionTitle;

	private Double transectionAmount;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "typeId")
	private TransectionType transectionType;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "categoryId")
	private TransectionCategory transectionCategory;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private User user;

	@Temporal(TemporalType.DATE)
	private Date transectionDate;

	@Override
	public String toString() {
		return "Transection [transectionId=" + transectionId + ", transectionTitle=" + transectionTitle + ", transectionAmount=" + transectionAmount
				+ ", transectionDate=" + transectionDate + "]";
	}

}
