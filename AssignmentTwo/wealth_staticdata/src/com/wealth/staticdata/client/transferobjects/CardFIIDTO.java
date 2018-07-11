package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;


public class CardFIIDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cardType;
	private Integer fiid;


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public Integer getFiid() {
		return fiid;
	}


	public void setFiid(Integer fiid) {
		this.fiid = fiid;
	}


	@Override
	public String toString() {
		return "cardType:"+cardType+" fiid:"+fiid;
	}
}
