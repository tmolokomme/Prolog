package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.CardTypeTO;

public interface CardTypeService {
	public CardTypeTO[] fetchAllCardTypes() throws ServerException;
	public CardTypeTO getCardTypeByCardType(int cardType) throws ServerException;
}
