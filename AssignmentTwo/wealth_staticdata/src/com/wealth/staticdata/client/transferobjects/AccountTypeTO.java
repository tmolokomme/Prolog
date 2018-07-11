package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;

import com.wealth.staticdata.client.enums.AccountTypesEnum;

public class AccountTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean active;
	private AccountTypesEnum types;
	private Integer id;


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public AccountTypesEnum getTypes() {
		return types;
	}

	public void setTypes(AccountTypesEnum types) {
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
