package com.order.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.OrderDetailDao;
import com.order.entity.Order;
import com.order.entity.OrderDetail;
import com.order.service.OrderDetailService;

@Service
@Transactional(readOnly = false)
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailDao orderDetailDao;

	@Override
	public List<OrderDetail> getOrderDetails(Long id) {
		return orderDetailDao.getOrderDetails(id);
				
	}

	
	public void saveOrUpdate(OrderDetail order) {
		 orderDetailDao.saveOrUpdate(order);
		
	}


	@Override
	public void delete(Long id) {
		orderDetailDao.delete(id);
		
	}
}
