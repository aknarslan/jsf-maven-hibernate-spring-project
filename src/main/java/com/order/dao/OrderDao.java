package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.dao.common.GenericDao;
import com.order.entity.Order;



public interface OrderDao extends GenericDao<Order> {
	
	List<Order> getOrders();
	
	List<Order> getOrders(Map<String, Object> map);

}
