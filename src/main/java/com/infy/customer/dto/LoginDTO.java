package com.infy.customer.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {
	
	@NotNull(message = "customer.phoneno.required")
	Long phoneNo;
	
	@NotNull(message = "customer.password.required")
	String password;
	
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
