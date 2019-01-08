package com.order.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.Customer;
import com.order.entity.OrderType;
import com.order.entity.common.GenericEntityInterface;
import com.order.service.CustomerService;
import com.order.service.OrderTypeService;






@Repository
public class EntityConverter  implements Converter {
	
	

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderTypeService orderTypeService;	
		
	
	

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		String str = "";

		if (obj == null) {
			return str;
		}

		if (obj instanceof GenericEntityInterface) {
			GenericEntityInterface entity = ((GenericEntityInterface) obj);
			str = entity.getClass().getSimpleName();
			str += ":" + entity.getIdAsString();
		}
		return str;

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String key) {

		if (StringUtils.isEmpty(key)) {
			return null;
		}

		Object obj = null;
		String[] objectString = key.split(":");
		
		if(objectString.length < 2){
			return obj;
		}
		if(Customer.class.getSimpleName().equals(objectString[0]))
		obj = customerService.findByCustomerId(Long.parseLong(objectString[1]));
		
	//	else if (objectString[0].equals(OrderType.class.getSimpleName()))
			//obj = orderTypeService.findByOrderTypeId(Long.parseLong(objectString[1]));

		

		Hibernate.initialize(obj);
		return obj;
	}

	

}
