package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;

public class PropertyTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean active;
	private String name;
	private Integer id;


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "id:"+id+" active:"+active+" name:"+name;
	}
}
