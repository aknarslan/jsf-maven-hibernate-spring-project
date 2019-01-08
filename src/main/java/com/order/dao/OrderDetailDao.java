package com.order.dao;

import java.util.List;

import com.order.dao.common.GenericDao;
import com.order.entity.OrderDetail;

public interface OrderDetailDao extends GenericDao<OrderDetail> {
	

	List<OrderDetail> getOrderDetails(Long id);
	
	void delete(Long id);

}
