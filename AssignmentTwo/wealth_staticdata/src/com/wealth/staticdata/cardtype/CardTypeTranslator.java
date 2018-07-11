package com.wealth.staticdata.cardtype;

import com.wealth.staticdata.client.transferobjects.CardTypeTO;
import com.wealth.staticdata.domain.CardType;

public class CardTypeTranslator {
	public static CardTypeTO copyCardTypesTOFromCardTypes(CardType p) {
		CardTypeTO to = new CardTypeTO();
		to.setDescription(p.getDescription());
		to.setCardType(p.getCardType());		
		return to;
	}
	public static CardType copyCardTypesFromCardTypesTO(CardTypeTO p) {
		CardType to = new CardType();
		to.setDescription(p.getDescription());
		to.setCardType(p.getCardType());		
		return to;
	}

}
