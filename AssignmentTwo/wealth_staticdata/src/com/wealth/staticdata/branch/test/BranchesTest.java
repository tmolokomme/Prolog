package com.wealth.staticdata.branch.test;

import com.wealth.staticdata.branch.FNBBranchServiceImpl;
import com.wealth.staticdata.client.transferobjects.BranchTypeTO;
import com.wealth.testing.txn.TransactionEnabledTestCase;


public class BranchesTest extends TransactionEnabledTestCase {

	public BranchesTest() {
		super("BranchesTest", "wealth_staticdata/hibernate/StaticDataHibSessionFactory", "wealth_staticdata");
	}

	private static final FNBBranchServiceImpl service = new FNBBranchServiceImpl();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFetchAllBranches(){
		try {
			BranchTypeTO[] ts = service.fetchAllFNBBranches();
			for (BranchTypeTO branchTypeTO : ts) {
				System.out.println(branchTypeTO.toString());
			}
			assertTrue(ts.length > 0);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
