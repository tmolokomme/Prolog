package com.wealth.staticdata.client.enums;

public enum AccountTypesEnum {

	HOGAN(1,"Hogan"),
	PHOENIX(2,"Phoenix");
	
	private final int index;
	private final String displayName;
	
	private AccountTypesEnum(int index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public int getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
