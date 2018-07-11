package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;

import com.wealth.staticdata.client.enums.ContactTypeEnum;


public class ContactTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean active;
	private ContactTypeEnum types;
	private Integer id;


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ContactTypeEnum getTypes() {
		return types;
	}

	public void setTypes(ContactTypeEnum types) {
		this.types = types;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "id:"+id+" active:"+active+" types:"+types;
	}
}
