package com.order.entity.common;

import javax.persistence.Transient;

public interface GenericEntityInterface {

	@Transient
	String getIdAsString();

}
