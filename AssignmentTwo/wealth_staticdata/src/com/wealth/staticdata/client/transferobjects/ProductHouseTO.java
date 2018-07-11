package com.wealth.staticdata.client.transferobjects;
import java.io.Serializable;

import com.wealth.staticdata.client.enums.AccountTypesEnum;


public class ProductHouseTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private boolean active;
	private String description;
	private Integer id;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "id:"+id+" active:"+active+" Description:"+description;
	}

}
