package com.wealth.staticdata.cardfiid;

import com.wealth.staticdata.client.transferobjects.CardFIIDTO;
import com.wealth.staticdata.domain.CardFIID;

public class CardFIIDTranslator {
	public static CardFIIDTO copyCardFIIDsTOFromCardFIIDs(CardFIID p) {
		CardFIIDTO to = new CardFIIDTO();
		to.setCardType(p.getCardType());
		to.setFiid(p.getFiid());		
		return to;
	}
	public static CardFIID copyCardFIIDsFromCardFIIDsTO(CardFIIDTO p) {
		CardFIID to = new CardFIID();
		to.setCardType(p.getCardType());
		to.setFiid(p.getFiid());		
		return to;
	}

}
