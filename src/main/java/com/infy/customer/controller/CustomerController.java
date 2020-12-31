package com.infy.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.customer.dto.CustomerDTO;
import com.infy.customer.dto.LoginDTO;
import com.infy.customer.dto.UpdateDTO;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.service.CustomerService;

@RestController
@Validated
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value = "/customers")
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException {
		String response = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/customers/{phoneNo}")
	public ResponseEntity<CustomerDTO> getSpecificCustomer(@PathVariable("phoneNo") Long phoneNo) throws CustomerException {
		CustomerDTO response = customerService.getSpecificCustomer(phoneNo);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/customers/{phoneNo}")
	public ResponseEntity<String> updateCustomerAddress(@PathVariable("phoneNo") Long phoneNo, @Valid @RequestBody UpdateDTO updateDTO) throws CustomerException {
		String response = customerService.updateBalanceAmount(phoneNo, updateDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/customers/{phoneNo}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("phoneNo") Long phoneNo) throws CustomerException {
		String response = customerService.deleteCustomer(phoneNo);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/customers")
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) throws CustomerException {
		String response = customerService.authenticateCustomer(loginDTO);
		return ResponseEntity.ok(response);
	}
	
}
