package com.infy.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
