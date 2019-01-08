package com.order.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.order.dao.CustomerDao;
import com.order.dao.common.GenericDaoHibernateImpl;
import com.order.entity.Customer;


@Repository
public class CustomerDaoImpl extends GenericDaoHibernateImpl<Customer> implements CustomerDao, Serializable {
	
	
	

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings(value = { "unchecked" })
	public List<Customer> getCustomers() {
		List<Customer> customers = getCurrentSession().createQuery("from Customer").list();
		

		return customers;
		
		
	}

	@Override
	public Customer findByCustomerId(Long id) {
		Customer customer;
		String sql = "SELECT c FROM Customer c  WHERE c.id=:id";
		
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		
		return customer = (Customer) query.getSingleResult();
	}

}
