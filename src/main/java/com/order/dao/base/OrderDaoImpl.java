package com.order.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.order.dao.OrderDao;

import com.order.dao.common.GenericDaoHibernateImpl;
import com.order.entity.Order;


@Repository
public class OrderDaoImpl extends GenericDaoHibernateImpl<Order> implements OrderDao, Serializable{

	@PersistenceUnit
	EntityManagerFactory emf;

	public OrderDaoImpl() {
		super(Order.class);
		
	}

	private static final long serialVersionUID = 1L;


	@Override
	@SuppressWarnings(value = { "unchecked" })
	public List<Order> getOrders() {
		List<Order> orders = getCurrentSession().createQuery("from Order").list();
		return orders;
	}


	@Override
	@SuppressWarnings(value = { "unchecked" })
	public List<Order> getOrders(Map<String, Object> map) {
		
		EntityManager entityManager = emf.createEntityManager();
		
		@SuppressWarnings("deprecation")
		Criteria criter = entityManager.unwrap(Session.class).createCriteria(Order.class);
		
		for (Entry<String, Object> filter : map.entrySet()) {
			
			String key = filter.getKey();
			Object value = filter.getValue();
			
			if(key.equals("customer")) {
				criter.add(Restrictions.eq("customer", value));
			}
			if(key.equals("orderType")) {
				criter.add(Restrictions.eq("orderType", value));
			}
			if(key.equals("status")) {
				criter.add(Restrictions.eq("status", value));
			}
			if(key.equals("explanation")) {
				criter.add(Restrictions.like("explanation", (String) value, MatchMode.START));
			}
			if(key.equals("orderId")) {
				criter.add(Restrictions.eq("id", value));
			}
			
		}
		return criter.list();
	}
}
