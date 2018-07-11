package com.wealth.staticdata.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.wealth.domain.BaseDomainEntity;
import com.wealth.staticdata.client.enums.PropertyTypeEnum;

@Entity
@Embeddable
public class PropertyType extends BaseDomainEntity {

	private static final long serialVersionUID = 1L;

	private boolean active;
	
	@Enumerated(EnumType.STRING)
	private PropertyTypeEnum types;

	public PropertyType() {
	}

	public PropertyType(boolean active, PropertyTypeEnum types) {
		super();
		this.active = active;
		this.types = types;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public PropertyTypeEnum getTypes() {
		return types;
	}

	public void setTypes(PropertyTypeEnum types) {
		this.types = types;
	}

}
