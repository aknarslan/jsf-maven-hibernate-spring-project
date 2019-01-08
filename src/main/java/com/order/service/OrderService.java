package com.order.service;

import java.util.List;
import java.util.Map;

import com.order.entity.Order;

public interface OrderService {
	
	 List<Order> getOrders();
	 
	 void saveOrUpdate(Order order);
	 
	 List<Order> getOrders(Map<String,Object> map);

}
