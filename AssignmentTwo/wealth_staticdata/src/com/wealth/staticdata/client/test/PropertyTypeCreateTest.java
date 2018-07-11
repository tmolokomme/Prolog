package com.wealth.staticdata.client.test;

import com.wealth.staticdata.client.enums.PropertyTypeEnum;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;
import com.wealth.staticdata.property.PropertyTypeServiceImpl;

import junit.framework.TestCase;

public class PropertyTypeCreateTest extends TestCase {
	private static final PropertyTypeServiceImpl service = new PropertyTypeServiceImpl();

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateNewPropertyType(){
		try {
			PropertyTypeTO newPropertyTypeTO = new PropertyTypeTO();
			newPropertyTypeTO.setActive(true);
			newPropertyTypeTO.setName(PropertyTypeEnum.House.getDisplayName());
			service.createPropertyType(newPropertyTypeTO);
			
			PropertyTypeTO newPropertyType = service.getPropertyTypeById(newPropertyTypeTO.getId());
			assertNotNull(newPropertyType);
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void testFetchAllActive(){
		try {
			PropertyTypeTO[] ts = service.getActivePropertyTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testFetchAll(){
		try {
			PropertyTypeTO[] ts = service.getPropertyTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
