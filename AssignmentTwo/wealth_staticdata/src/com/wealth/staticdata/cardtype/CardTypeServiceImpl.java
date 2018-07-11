package com.wealth.staticdata.cardtype;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;

import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.CardTypeLocal;
import com.wealth.staticdata.client.interfaces.ejb.CardTypeRemote;
import com.wealth.staticdata.client.transferobjects.CardTypeTO;

@Stateless
@WebService
public class CardTypeServiceImpl implements CardTypeLocal, CardTypeRemote {
    
	private CardTypeTransactions cardTypeTxs = new CardTypeTransactions();
		
	public CardTypeTO[] fetchAllCardTypes() throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return cardTypeTxs.fetchAllCardTypes(session);
	}

	public CardTypeTO getCardTypeByCardType(int cardType) throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return cardTypeTxs.getCardTypeByCardType(session, cardType);
	}

}