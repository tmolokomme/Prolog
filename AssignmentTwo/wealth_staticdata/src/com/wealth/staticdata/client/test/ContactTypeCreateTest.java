package com.wealth.staticdata.client.test;

import com.wealth.staticdata.client.enums.ContactTypeEnum;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;
import com.wealth.staticdata.contact.ContactTypeServiceImpl;

import junit.framework.TestCase;

public class ContactTypeCreateTest extends TestCase {
	private static final ContactTypeServiceImpl service = new ContactTypeServiceImpl();

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateNewContactType(){
		try {
			ContactTypeTO newContactTypeTO = new ContactTypeTO();
			newContactTypeTO.setActive(true);
			newContactTypeTO.setTypes(ContactTypeEnum.Owner);
			service.createContactType(newContactTypeTO);
			
			ContactTypeTO newContactType = service.getContactTypeById(newContactTypeTO.getId());
			assertNotNull(newContactType);
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void testFetchAllActive(){
		try {
			ContactTypeTO[] ts = service.getActiveContactTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testFetchAll(){
		try {
			ContactTypeTO[] ts = service.getContactTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
