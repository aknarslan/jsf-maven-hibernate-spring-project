package com.order.service;

import java.util.List;

import com.order.entity.OrderType;

public interface OrderTypeService {
	
	List<OrderType> getOrderTypes();
	
	OrderType findById(Long id);

}
