package com.wealth.staticdata.cardfiid;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.CardFIIDTO;
import com.wealth.staticdata.domain.CardFIID;
import com.wealth.staticdata.domain.CardType;

public class CardFIIDTransactions {

	private CardFIIDTO[] convertToTOArray(List<CardFIID> types) {
		CardFIIDTO[] typesTO = new CardFIIDTO[types.size()];
		int i = 0;
		for (CardFIID cl : types) {
			typesTO[i] = CardFIIDTranslator.copyCardFIIDsTOFromCardFIIDs(cl);
			i++;
		}
		return typesTO;
	}

	public CardFIIDTO[] fetchAllCardFIIDs(Session session) {
		try {			
			Query query = session.createQuery("from CardFIID order by cardFIID asc");
			List<CardFIID> types = query.list();
			CardFIIDTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
	}

	public CardFIIDTO getCardFIIDByCardFIID(Session session, Integer cardFIID) throws ServerException {		
		try {					
			CardFIID c = (CardFIID)session.createCriteria(CardFIID.class)
			.add( Restrictions.eq("fiid",  cardFIID) )
			.uniqueResult();
		
			if (c == null)
				return null;

			CardFIIDTO cardFIIDTO = CardFIIDTranslator.copyCardFIIDsTOFromCardFIIDs(c);
			return cardFIIDTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}
	
}
