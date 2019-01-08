package com.order.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import com.order.entity.Customer;
import com.order.entity.Order;
import com.order.entity.OrderDetail;
import com.order.entity.OrderType;


@ManagedBean
@ViewScoped
public class NewOrderDetailBean extends GenericBean {
	
	private static final long serialVersionUID = 1L;
	private List<Customer> customers;
	private List<OrderType> orderTypes;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	private List<OrderDetail> tmpOrderDetails = new ArrayList<OrderDetail>();
	
	private Order newOrder = new Order();
	private Customer selectedCustomer = new Customer();;
	private OrderType selectedOrderType = new OrderType();;
	
	private String newProduct;
	
	private Long quantity;
	private Long customerId;
	
	private BigDecimal price;
	private BigDecimal totalPrice = BigDecimal.ZERO;
	
	private String orderType;
	
	
	public void init(){
		customers = getCustomerService().getCustomers();
		orderTypes = getOrderTypeService().getOrderTypes();
		
	}
	
	public BigDecimal calculatePrice(BigDecimal price, Long quantity) {
	    BigDecimal quantityConvert = new BigDecimal(quantity);
	    BigDecimal totalPrice = quantityConvert.multiply(price);
	    this.totalPrice = totalPrice;
		return totalPrice;
	}
	
	public void saveDetails() {
		if(StringUtils.isEmpty(newProduct)) {
			addWarnMessage("�r�n Tipi Bo�", "�r�n Tipi Bo� B�rak�lamaz");
			return;
		}
		if(quantity == null) {
			addWarnMessage("�r�n Adeti Bo�", "�r�n Adeti Bo� B�rak�lamaz");
			return;
		}
		if(price == null) {
			addWarnMessage("�r�n Fiyat� Bo�", "�r�n Fiyat� Bo� B�rak�lamaz");
			return;
		}
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setProduct(newProduct);
		orderDetail.setPrice(price);
		orderDetail.setQuantity(quantity);
		orderDetails.add(orderDetail);
		tmpOrderDetails = orderDetails;
		getRequestContext().execute("PF('detailDialog').hide();");
		addInfoMessage("Sipari� Kalemi Eklendi", "Sipari� kalemi Ba�ar�yla eklendi");
		
		
	}
	
	public void redirectToOrderListPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/index.xhtml");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	public void saveOrder() {
		 selectedCustomer= getCustomerService().findByCustomerId(customerId);
		 
//		if(CollectionUtils.isEmpty(tmpOrderDetails)) {
//			addWarnMessage("Sipari� Detay� Bo�", "L�tfen Sipari� Detay� Tan�mlay�n�z");
//			return;
//		}
		if(selectedCustomer.getName() == null) {
			addWarnMessage("M��teri Se�imi Bo�", "L�tfen M��teri Se�imi Yap�n�z");
			return;
		}
		if(StringUtils.isEmpty(newOrder.getExplanation()) || StringUtils.isEmpty(newOrder.getShippingAddress())) {
			addWarnMessage("Sipari� Bilgileri (A��klama/Adres) Bo�", "L�tfen �lgili alanlar� Bo� B�rakmay�n�z");
			return;
		}
		
		newOrder.setCustomer(selectedCustomer);
		newOrder.setOrderDetails(tmpOrderDetails);
		newOrder.setStatus("NEW");
		newOrder.setOrderType(selectedOrderType.getTypeName());
		
		getOrderService().saveOrUpdate(newOrder);
		addInfoMessage("��lem ba�ar�l�", "Sipari� Ba�ar�yla Kaydedildi.");
		clearPage();
	}
	
	private void clearPage() {
		orderDetails.clear();
		selectedCustomer = null;
		newOrder.setCustomer(null);
		newOrder.setExplanation("");
		newOrder.setStatus("");
		newOrder.setShippingAddress("");
		newOrder.setOrderDetails(null);
		newOrder.setOrderType(null);
		newProduct = null;
		price = null;
		quantity = null;
	}
	
	

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<OrderType> getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(List<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}

	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderType getSelectedOrderType() {
		return selectedOrderType;
	}

	public void setSelectedOrderType(OrderType selectedOrderType) {
		this.selectedOrderType = selectedOrderType;
	}


	public String getNewProduct() {
		return newProduct;
	}


	public void setNewProduct(String newProduct) {
		this.newProduct = newProduct;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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

	public List<OrderDetail> getTmpOrderDetails() {
		return tmpOrderDetails;
	}

	public void setTmpOrderDetails(List<OrderDetail> tmpOrderDetails) {
		this.tmpOrderDetails = tmpOrderDetails;
	}

}
