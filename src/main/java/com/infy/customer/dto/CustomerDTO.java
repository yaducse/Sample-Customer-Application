package com.infy.customer.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.infy.customer.entity.Customer;
import com.infy.customer.utility.validator.ValidateAccountType;
import com.infy.customer.utility.validator.ValidateBalanceAmount;

public class CustomerDTO {
	
	@NotNull(message = "customer.phoneno.required")
	@Min(value = 1000000000L, message = "customer.phoneno.invalid")
    @Max(value = 9999999999L, message = "customer.phoneno.invalid")
	Long phoneNo;
	
	@NotNull(message = "customer.name.required")
	String name;
	
	@NotNull(message = "customer.account.type.required")
	@ValidateAccountType(message = "customer.account.type.invalid")
	String accountType;
	
	@NotNull(message = "customer.balance.amount.required")
	@ValidateBalanceAmount(message = "customer.balance.amount.invalid")
	Double balanceAmount;
	
	@NotNull(message = "customer.password.required")
	String password;
	
	public CustomerDTO() {}
	
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
	public static CustomerDTO prepareCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setPhoneNo(customer.getPhoneNo());
		customerDTO.setName(customer.getName());
		customerDTO.setAccountType(customer.getAccountType());
		customerDTO.setBalanceAmount(customer.getBalanceAmount());
		customerDTO.setPassword(customer.getPassword());
		
		return customerDTO;
	}
	
	public static Customer prepareCustomerEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		
		customer.setPhoneNo(customerDTO.getPhoneNo());
		customer.setName(customerDTO.getName());
		customer.setAccountType(customerDTO.getAccountType());
		customer.setBalanceAmount(customerDTO.getBalanceAmount());
		customer.setPassword(customerDTO.getPassword());
		
		return customer;
	}
	
}
