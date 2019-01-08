package com.order.service.base;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.order.dao.CustomerDao;
import com.order.entity.Customer;
import com.order.service.CustomerService;

@Service
@Transactional(readOnly = false)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> getCustomers() {
		
		return customerDao.getCustomers();
	}

	@Override
	public Customer findById(Long id) {
		return customerDao.findById(id);
	}

	@Override
	public Customer findByCustomerId(Long id) {
		
		return customerDao.findByCustomerId(id);
	}

	
	
	

}
