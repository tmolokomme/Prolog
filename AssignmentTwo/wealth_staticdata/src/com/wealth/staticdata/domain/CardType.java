package com.wealth.staticdata.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wealth.domain.BaseDomainEntity;

@Entity
@Table(name="CardTypes")
@Embeddable
public class CardType extends BaseDomainEntity{

	private static final long serialVersionUID = 1L;
	@Column(name="CardType")
    private Integer cardType;
	
	@Column(name="Description")
    private String description;
    
	public CardType() {
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
