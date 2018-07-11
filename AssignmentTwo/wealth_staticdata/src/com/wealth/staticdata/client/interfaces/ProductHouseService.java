package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.ProductHouseTO;

public interface ProductHouseService {
	
	public ProductHouseTO[] getProductHouseDetails() throws ServerException;
	public ProductHouseTO getProductHouseDetailsById(int id)throws ServerException;

}
