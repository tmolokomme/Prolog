package com.wealth.staticdata.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wealth.domain.BaseDomainEntity;

@Entity
@Table(name="CardFIID")
@Embeddable
public class CardFIID extends BaseDomainEntity{

	private static final long serialVersionUID = 1L;
	@Column(name="FIID")
    private Integer fiid;
	
	@Column(name="CardType")
    private String cardType;
    
	public CardFIID() {
	}


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

	public String toString(){
		return "cardType:"+cardType+" fiid:"+fiid;
	}
}
