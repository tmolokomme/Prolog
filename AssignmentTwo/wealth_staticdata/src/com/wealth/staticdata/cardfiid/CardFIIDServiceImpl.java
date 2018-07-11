package com.wealth.staticdata.cardfiid;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;

import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.CardFIIDLocal;
import com.wealth.staticdata.client.interfaces.ejb.CardFIIDRemote;
import com.wealth.staticdata.client.interfaces.ejb.CardTypeLocal;
import com.wealth.staticdata.client.interfaces.ejb.CardTypeRemote;
import com.wealth.staticdata.client.transferobjects.CardFIIDTO;
import com.wealth.staticdata.client.transferobjects.CardTypeTO;

@Stateless
@WebService
public class CardFIIDServiceImpl implements CardFIIDLocal, CardFIIDRemote {
    
	private CardFIIDTransactions cardFIIDTxs = new CardFIIDTransactions();
		
	public CardFIIDTO[] fetchAllCardFIIDs() throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return cardFIIDTxs.fetchAllCardFIIDs(session);
	}

	public CardFIIDTO getCardFIIDByFIID(int fiid) throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return cardFIIDTxs.getCardFIIDByCardFIID(session, fiid);
	}

}