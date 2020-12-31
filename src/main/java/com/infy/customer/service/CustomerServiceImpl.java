package com.infy.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infy.customer.dto.CustomerDTO;
import com.infy.customer.dto.LoginDTO;
import com.infy.customer.dto.UpdateDTO;
import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.repository.CustomerRepository;
import com.infy.customer.utility.CustomerConstants;

@Service("CustomerService")
@PropertySource("classpath:message.properties")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Environment environment;

	@Override
	public String createCustomer(CustomerDTO customerDTO) throws CustomerException {
		// TODO Auto-generated method stub
		boolean customerFound = customerRepository.existsById(customerDTO.getPhoneNo());
		
		if (customerFound) {
			throw new CustomerException(CustomerConstants.CUSTOMER_NOT_UNIQUE.toString());
		}
		
		customerRepository.save(CustomerDTO.prepareCustomerEntity(customerDTO));
		return environment.getProperty(CustomerConstants.CUSTOMER_REGISTER_SUCCESS.toString());
	}

	@Override
	public CustomerDTO getSpecificCustomer(Long phoneNo) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> customerOptional = customerRepository.findById(phoneNo);
		
		if (customerOptional.isEmpty()) {
			throw new CustomerException(CustomerConstants.CUSTOMER_NOT_FOUND.toString());
		}
		
		Customer customer = customerOptional.get();
	
		return CustomerDTO.prepareCustomerDTO(customer);
	}

	@Override
	public String updateBalanceAmount(Long phoneNo, UpdateDTO updateDTO) throws CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Customer> customerOptional = customerRepository.findById(phoneNo);
		
		if (customerOptional.isEmpty()) {
			throw new CustomerException(CustomerConstants.CUSTOMER_NOT_FOUND.toString());
		}
		
		Customer customer = customerOptional.get();
		customer.setBalanceAmount(updateDTO.getBalanceAmount());
		customerRepository.save(customer);
		return environment.getProperty(CustomerConstants.CUSTOMER_UPDATE_SUCCESS.toString());
	}

	@Override
	public String deleteCustomer(Long phoneNo) throws CustomerException {
		// TODO Auto-generated method stub
		boolean customerFound = customerRepository.existsById(phoneNo);
		
		if (!customerFound) {
			throw new CustomerException(CustomerConstants.CUSTOMER_NOT_FOUND.toString());
		}
		
		customerRepository.deleteById(phoneNo);
		return environment.getProperty(CustomerConstants.CUSTOMER_DELETE_SUCCESS.toString());
	}

	@Override
	public String authenticateCustomer(LoginDTO loginDTO) throws CustomerException {
		// TODO Auto-generated method stub
		Long phoneNo = loginDTO.getPhoneNo();
		String password = loginDTO.getPassword();
		
		Optional<Customer> customerOptional = customerRepository.findById(phoneNo);
		
		if (customerOptional.isEmpty()) {
			throw new CustomerException(CustomerConstants.CUSTOMER_NOT_FOUND.toString());
		}
		
		Customer customer = customerOptional.get();
		
		if (customer.getPassword().equals(password)) {
			return environment.getProperty(CustomerConstants.CUSTOMER_LOGIN_SUCCESS.toString());
		} throw new CustomerException(CustomerConstants.CUSTOMER_LOGIN_FAILURE.toString());
	}

}
