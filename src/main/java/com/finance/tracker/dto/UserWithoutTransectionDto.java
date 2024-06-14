package com.finance.tracker.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Kody Technolab Ltd. <br/>
 * @date    : 25-Apr-2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithoutTransectionDto {

	private Long userId;

	private String name;

	private Date dob;

	private int age;

	private String mobileNumber;

	private String email;

	private String password;

	private Double userWalletBalanceDouble;
}
