package com.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.order.entity.common.GenericEntityInterface;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetail implements Serializable, GenericEntityInterface{
	
	private static final long serialVersionUID = 4440935290669028798L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Order order;
	
	@Column(name= "PRODUCT")
	private String product;
	
	@Column(name= "QUANTITY")
	private Long quantity;
	
	@Column(name= "PRICE")
	private BigDecimal price;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@Transient
	public String getIdAsString() {
		return String.valueOf(id);
	}

}
