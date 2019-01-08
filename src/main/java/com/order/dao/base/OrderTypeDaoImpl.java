package com.order.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.order.dao.OrderTypeDao;
import com.order.dao.common.GenericDaoHibernateImpl;
import com.order.entity.OrderDetail;
import com.order.entity.OrderType;

@Repository
public class OrderTypeDaoImpl extends GenericDaoHibernateImpl<OrderType> implements OrderTypeDao , Serializable {

	private static final long serialVersionUID = -737796469106055833L;
	
	 public OrderTypeDaoImpl() {
		super(OrderType.class);
	}

	@Override
	@SuppressWarnings(value = { "unchecked" })
	public List<OrderType> getOrderTypes() {
		List<OrderType> orderTypes = getCurrentSession().createQuery("from OrderType").list();
		return  orderTypes;
	}
}
