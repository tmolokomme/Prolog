package com.wealth.staticdata.domain;


import javax.persistence.Embeddable;
import javax.persistence.Entity;

import com.wealth.domain.BaseDomainEntity;


@Entity
@Embeddable
public class ProductHouse extends BaseDomainEntity {
	private static final long serialVersionUID = 1L;
	
	private boolean active;
	private String description;
	

	public ProductHouse() {
	}

	public ProductHouse(boolean active, String description) {
		super();
		this.active = active;
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
