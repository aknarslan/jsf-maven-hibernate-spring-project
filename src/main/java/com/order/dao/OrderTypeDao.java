package com.order.dao;

import java.util.List;

import com.order.dao.common.GenericDao;
import com.order.entity.OrderType;

public interface OrderTypeDao extends GenericDao<OrderType>  {

	List<OrderType> getOrderTypes();


}
