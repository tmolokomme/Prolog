package com.wealth.staticdata.client.test;

import com.wealth.staticdata.account.AccountTypeServiceImpl;
import com.wealth.staticdata.client.enums.AccountTypesEnum;
import com.wealth.staticdata.client.transferobjects.AccountTypeTO;

import junit.framework.TestCase;

public class AccountTypesCreateTest extends TestCase {
	private static final AccountTypeServiceImpl service = new AccountTypeServiceImpl();

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateNewAccountTypes(){
		try {
			AccountTypeTO newAccountTypesTO = new AccountTypeTO();
			newAccountTypesTO.setActive(true);
			newAccountTypesTO.setTypes(AccountTypesEnum.HOGAN);
			service.createAccountTypes(newAccountTypesTO);
			
			AccountTypeTO newAccountTypes = service.getAccountTypesById(newAccountTypesTO.getId());
			assertNotNull(newAccountTypes);
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void testFetchAllActive(){
		try {
			AccountTypeTO[] ts = service.getActiveAccountTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void testFetchAll(){
		try {
			AccountTypeTO[] ts = service.getAccountTypes();
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
