package com.order.service;

import java.util.List;

import com.order.entity.OrderDetail;

public interface OrderDetailService {
	
	List<OrderDetail> getOrderDetails(Long id);
	

	void saveOrUpdate(OrderDetail order);
	
	
	void delete(Long id);

}
