package com.order.service;

import java.util.List;

import com.order.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	Customer findById(Long id);
	
	Customer findByCustomerId(Long id);

}
