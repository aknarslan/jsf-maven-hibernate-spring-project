package com.order.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.order.service.CustomerService;
import com.order.service.OrderDetailService;
import com.order.service.OrderService;
import com.order.service.OrderTypeService;

public class GenericBean implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{customerService}")
	 private CustomerService customerService;
	
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	
	@ManagedProperty(value = "#{orderTypeService}")
	private OrderTypeService orderTypeService;
	
	@ManagedProperty(value = "#{orderDetailService}")
	private OrderDetailService orderDetailService;
	
	public void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail, null);
	}

	public void addWarnMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail, null);
	}

	public void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail, null);
	}

	public void addInfoMessage(String summary, String detail, String clientId) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail, clientId);
	}

	public void addWarnMessage(String summary, String detail, String clientId) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail, clientId);
	}

	public void addErrorMessage(String summary, String detail, String clientId) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail, clientId);
	}

	private void addMessage(Severity severity, String summary, String detail, String clientId) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		getRequestContext().showMessageInDialog(message);
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public OrderTypeService getOrderTypeService() {
		return orderTypeService;
	}

	public void setOrderTypeService(OrderTypeService orderTypeService) {
		this.orderTypeService = orderTypeService;
	}

	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

}
