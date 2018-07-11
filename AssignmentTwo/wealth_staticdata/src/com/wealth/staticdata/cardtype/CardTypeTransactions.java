package com.wealth.staticdata.cardtype;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.CardTypeTO;
import com.wealth.staticdata.domain.CardType;

public class CardTypeTransactions {

	private CardTypeTO[] convertToTOArray(List<CardType> types) {
		CardTypeTO[] typesTO = new CardTypeTO[types.size()];
		int i = 0;
		for (CardType cl : types) {
			typesTO[i] = CardTypeTranslator.copyCardTypesTOFromCardTypes(cl);
			i++;
		}
		return typesTO;
	}

	public CardTypeTO[] fetchAllCardTypes(Session session) {
		try {			
			Query query = session.createQuery("from CardType order by cardType asc");
			List<CardType> types = query.list();
			CardTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
	}

	public CardTypeTO getCardTypeByCardType(Session session, Integer cardType) throws ServerException {		
		try {			
			CardType c = (CardType)session.createCriteria(CardType.class)
    				.add( Restrictions.eq("cardType",  cardType) )
    				.uniqueResult();
			
			if (c == null)
				return null;

			CardTypeTO cardTypeTO = CardTypeTranslator.copyCardTypesTOFromCardTypes(c);
			return cardTypeTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

}
