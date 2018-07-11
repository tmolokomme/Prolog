package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;


public class CardTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private Integer cardType;

	public String getDescription() {
		return description;
	}
	
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "cardType:"+cardType+" description:"+description;
	}
}
