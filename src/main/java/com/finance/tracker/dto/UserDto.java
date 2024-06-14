package com.finance.tracker.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

	private Long id;

	@NotBlank(message = "{register.name}")
	private String name;

	private Date dob;

	@NotNull(message = "{register.age}")
	private int age;

	@Pattern(regexp = "^\\d{10}$", message = "{register.number}")
	@NotBlank(message = "{register.number.blank}")
	private String mobileNumber;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "{register.email}")
	@NotBlank(message = "{register.email.blank}")
	private String email;

	@Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+_=])\\S{8,}\\z", message = "{register.password}")
	private String password;

	@Min(1)
	@Max(10000000)
	private Double userWalletBalanceDouble;

	private List<TransectionDtos> transectionDtos;

}
