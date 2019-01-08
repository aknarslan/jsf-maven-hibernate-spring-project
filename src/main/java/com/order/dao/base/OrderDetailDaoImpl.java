package com.order.dao.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.order.dao.OrderDetailDao;
import com.order.dao.common.GenericDaoHibernateImpl;
import com.order.entity.OrderDetail;

@Repository
public class OrderDetailDaoImpl extends GenericDaoHibernateImpl<OrderDetail> implements OrderDetailDao, Serializable {

	public OrderDetailDaoImpl() {
		super(OrderDetail.class);
		
	}

	private static final long serialVersionUID = -737796469106055833L;

	@Override
	@SuppressWarnings(value = { "unchecked" })
	public List<OrderDetail> getOrderDetails(Long id) {
		
		String sql = "SELECT d FROM OrderDetail d  WHERE d.order.id=:id ORDER BY ID DESC";
		
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void delete(Long id) {
		
		String sql = "DELETE  FROM OrderDetail   WHERE id=:id";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	


	
}
