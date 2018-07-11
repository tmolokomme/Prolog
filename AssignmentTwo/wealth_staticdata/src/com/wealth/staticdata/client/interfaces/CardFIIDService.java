package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.CardFIIDTO;

public interface CardFIIDService {
	public CardFIIDTO[] fetchAllCardFIIDs() throws ServerException;
	public CardFIIDTO getCardFIIDByFIID(int fiid) throws ServerException;
}
