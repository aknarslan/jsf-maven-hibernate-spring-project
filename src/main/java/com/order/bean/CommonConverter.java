package com.order.bean;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@ViewScoped
@FacesConverter(value="commonConverter") 
public class CommonConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String entity) {
		Map<Object, String> entries = getConverterCacheMap();
		
		for(Entry<Object, String> entry : entries.entrySet()){
			if(entry.getValue().equals(entity)){
				return entry.getKey();
			}
		}
		
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		
		Map<Object, String> entries = getConverterCacheMap();
		if(entity != null &&  StringUtils.isNotEmpty(entity.toString()) &&  !entries.containsKey(entity)){
			String mapId =  entity.toString();
			entries.put(entity, mapId);
			return mapId;			
		}
		return entries.get(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	private Map<Object, String> getConverterCacheMap(){
		 Map<Object, String> converterCacheMap =  (Map<Object, String>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("converterCacheMap");
		 if(converterCacheMap == null){
			 converterCacheMap = new HashMap<>();
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("converterCacheMap",converterCacheMap);
		 }
		 return converterCacheMap;
	}
	 
	}
	


