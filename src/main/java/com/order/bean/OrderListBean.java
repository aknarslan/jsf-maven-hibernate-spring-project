package com.order.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.order.entity.Customer;
import com.order.entity.Order;
import com.order.entity.OrderType;


@ManagedBean
@ViewScoped
public class OrderListBean extends GenericBean {
	


	private static final long serialVersionUID = 1L;
	
	private List<Customer> customers;
	 private List<Order> orders;
	 private List<OrderType> orderTypes;
	 
	 private Customer customer ;
	 
	 private Long customerId;
	 
	 private String status;
	 private String explanation;
	 private String orderType;
	 
	 private Long orderId;
	
	 private Map<String, Object> filters = new HashMap<>();
	
	@PostConstruct
	public void init(){
		customers = getCustomerService().getCustomers();
		orders = getOrderService().getOrders();
		orderTypes = getOrderTypeService().getOrderTypes();
	}
	
	public void redirectToNewOrderDetailPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/new-order-detail.xhtml");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	public void redirectToOrderDetailPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/order-detail.xhtml");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	public void filterOrders() {
		
		if(customerId != null) {
			for(Customer customer : customers) {
				int i =customer.getId().compareTo(customerId);
				if(i == 0){
					filters.put("customer", customer);
				}	
			}
			
		}
		if(StringUtils.isNotEmpty(orderType)) {
			filters.put("orderType", orderType);
		}
		if(StringUtils.isNotEmpty(status)) {
			filters.put("status", status);
		}
		if(StringUtils.isNotEmpty(explanation)) {
			filters.put("explanation", explanation);
		}
		if(orderId != null) {
			filters.put("orderId", orderId);
		}
		
		orders = getOrderService().getOrders(filters);
		
	}

	

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<OrderType> getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(List<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	
	

}
