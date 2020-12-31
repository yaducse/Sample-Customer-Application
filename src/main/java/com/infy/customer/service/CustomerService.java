package com.infy.customer.service;

import com.infy.customer.dto.CustomerDTO;
import com.infy.customer.dto.LoginDTO;
import com.infy.customer.dto.UpdateDTO;
import com.infy.customer.exception.CustomerException;

public interface CustomerService {
	
	public String createCustomer(CustomerDTO customerDTO) throws CustomerException;
	
	public CustomerDTO getSpecificCustomer(Long phoneNo) throws CustomerException;
		
	public String updateBalanceAmount(Long phoneNo, UpdateDTO updateDTO) throws CustomerException;
	
	public String deleteCustomer(Long phoneNo) throws CustomerException;
	
	public String authenticateCustomer(LoginDTO loginDTO) throws CustomerException;
	
}
