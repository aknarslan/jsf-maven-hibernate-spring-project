package com.order.dao;

import java.util.List;

import com.order.dao.common.GenericDao;
import com.order.entity.Customer;


public interface CustomerDao extends GenericDao<Customer>  {
	
	public List<Customer> getCustomers();
	
	Customer findByCustomerId(Long id);

	

}
