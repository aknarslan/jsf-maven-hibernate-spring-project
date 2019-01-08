package com.order.bean;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.order.entity.Order;
import com.order.entity.OrderDetail;
import com.order.service.OrderDetailService;



@ManagedBean
@SessionScoped
public class OrderDetailBean extends GenericBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String newProduct;
	
	private List<OrderDetail> orderDetailList;
	
	private Long quantity;
	
	private OrderDetail selectedOrderDetail;
	private Order selectedOrder;
	
	private BigDecimal totalPrice;
	private BigDecimal price;
	
	private List<BigDecimal> prices = new ArrayList<BigDecimal>();
	
	

	
	
	public BigDecimal calculatePrice(BigDecimal price, Long quantity) {
	    BigDecimal quantityConvert = new BigDecimal(quantity);
	    BigDecimal totalPrice = quantityConvert.multiply(price);
	    prices.add(totalPrice);
		return totalPrice;
	}
	
	public BigDecimal calculateTotalprice() {
		totalPrice = BigDecimal.ZERO;
		for(OrderDetail order: orderDetailList) {
			totalPrice = totalPrice.add(order.getPrice().multiply(new BigDecimal(order.getQuantity())));
		}
		return totalPrice;
	}
	
	public void saveDetails() throws Exception {
		
		if(StringUtils.isEmpty(getNewProduct())) {
			addWarnMessage("Ürün Tipi Boþ", "Ürün Tipi Boþ Býrakýlamaz");
			return;
		}
		if(getQuantity() == null) {
			addWarnMessage("Ürün Adeti Boþ", "Ürün Adeti Boþ Býrakýlamaz");
			return;
		}
		if(getPrice() == null) {
			addWarnMessage("Ürün Fiyatý Boþ", "Ürün Fiyatý Boþ Býrakýlamaz");
			return;
		}
		OrderDetail orderDetail = new OrderDetail();
		
		try {
			orderDetail.setOrder(selectedOrder);
			orderDetail.setPrice(price);
			orderDetail.setQuantity(quantity);
			orderDetail.setProduct(newProduct);
			
			getOrderDetailService().saveOrUpdate(orderDetail);
			addInfoMessage("Ýþlem Baþarýlý", "Detay Ekleme Ýþlemi baþarýlý");
			getRequestContext().execute("PF('detailDialog').hide();");
		}catch (Exception e) {
			throw new Exception("Hata" +e.getMessage());
		}
		
		clearDetailFields();
	}
	
	public void deleteOrderDetail() throws Exception {
		try {
			getOrderDetailService().delete(selectedOrderDetail.getId());
			addInfoMessage("Ýþlem Baþarýlý", "Silme Ýþlemi baþarýlý");
			getRequestContext().update("form");
		}catch (Exception e) {
			throw new Exception("Silme Ýþleminde Hata"+ e.getMessage());
		}
		
		
	}
	
	public void approveOrder() throws Exception {
		try {
			selectedOrder.setStatus("APPROVED");
			getOrderService().saveOrUpdate(selectedOrder);
			addInfoMessage("Ýþlem Baþarýlý", "Onay Ýþlemi baþarýlý");
			getRequestContext().update("form");
		}catch (Exception e) {
			throw new Exception("Onaylama Ýþleminde Hata"+ e.getMessage());
		}
		
	}
	
	private void clearDetailFields() {
		newProduct="";
		quantity = null;
		price = null;
	}
	
	
	public void openDetailPage() {
		orderDetailList = getOrderDetailService().getOrderDetails(selectedOrder.getId());
		prices.clear();
		calculateTotalprice();
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
	
	public boolean checkApprove() {
		
		if(selectedOrder.getStatus().equals("APPROVED")) {
			return true;
		}
		return false;
	}
	
	
	public void openNewDetailDialog() {
		//:TODO 
	}


	public Order getSelectedOrder() {
		return selectedOrder;
	}


	public void setSelectedOrder(Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public OrderDetail getSelectedOrderDetail() {
		return selectedOrderDetail;
	}

	public void setSelectedOrderDetail(OrderDetail selectedOrderDetail) {
		this.selectedOrderDetail = selectedOrderDetail;
	}

	public List<BigDecimal> getPrices() {
		return prices;
	}

	public void setPrices(List<BigDecimal> prices) {
		this.prices = prices;
	}

	

}
