package com.order.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import com.order.service.OrderService;

@Service
@Transactional(readOnly = false)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Override
	public void saveOrUpdate(Order order) {
		orderDao.saveOrUpdate(order);
		
	}

	@Override
	public List<Order> getOrders(Map<String, Object> map) {
		
		return orderDao.getOrders(map);
	}

}
