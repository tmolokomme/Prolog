package com.wealth.staticdata.property;

import com.wealth.staticdata.client.enums.PropertyTypeEnum;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;
import com.wealth.staticdata.domain.PropertyType;

public class PropertyTypeTranslator {
    
	public static PropertyTypeTO copyPropertyTypeTOFromPropertyType(PropertyType p) {
		PropertyTypeTO to = new PropertyTypeTO();
		to.setActive(p.isActive());
		to.setName(p.getTypes().getDisplayName());
		to.setId(p.getId());
		return to;
	}
	public static PropertyType copyPropertyTypeFromPropertyTypeTO(PropertyTypeTO p) {
		PropertyType to = new PropertyType();
		to.setActive(p.isActive());
		
		PropertyTypeEnum[] types = PropertyTypeEnum.values();
		PropertyTypeEnum pointer = types[0];
		for (PropertyTypeEnum e : types)
		{
		    if (e.getDisplayName().equals(p.getName())) {
		        pointer = e;
		        break;
		    }
		}
		
		to.setTypes(pointer);
		to.setId(p.getId());
		return to;
	}
}
