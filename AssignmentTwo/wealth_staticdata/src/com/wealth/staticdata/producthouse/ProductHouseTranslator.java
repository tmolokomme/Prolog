package com.wealth.staticdata.producthouse;

import com.wealth.staticdata.client.transferobjects.ProductHouseTO;
import com.wealth.staticdata.domain.ProductHouse;

public class ProductHouseTranslator {
	
	public static ProductHouseTO copyProductHouseTOFromProductHouse(ProductHouse p) {
		
		ProductHouseTO to = new ProductHouseTO();
		to.setActive(p.isActive());
		to.setDescription(p.getDescription());
		to.setId(p.getId());
		return to;
	}

}
