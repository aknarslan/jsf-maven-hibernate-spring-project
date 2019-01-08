package com.order.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.OrderTypeDao;
import com.order.entity.OrderType;
import com.order.service.OrderTypeService;

@Service
@Transactional(readOnly = false)
public class OrderTypeServiceImpl implements OrderTypeService {

	@Autowired
	OrderTypeDao orderTypeDao;

	@Override
	public List<OrderType> getOrderTypes() {
		
		return orderTypeDao.getOrderTypes();
	}

	@Override
	public OrderType findById(Long id) {
		
		return orderTypeDao.findById(id);
	}
}
