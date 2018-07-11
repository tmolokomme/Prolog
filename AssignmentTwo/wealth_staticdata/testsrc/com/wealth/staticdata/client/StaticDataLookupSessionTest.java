package com.wealth.staticdata.client;

import java.util.Iterator;
import java.util.List;

import com.wealth.staticdata.StaticDataLookupSessionBean;
import com.wealth.testing.jdbc.DataSourceEnabledTestCase;
import com.wealth.client.ServerException;

public class StaticDataLookupSessionTest extends DataSourceEnabledTestCase {

    public StaticDataLookupSessionTest(String testname) {
        super(testname);
    }
    
    @Override
    protected void setUp() throws Exception {        
        super.setUp();        
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testFNBPCBranchLookups() {
        StaticDataLookupSession sdls = new StaticDataLookupSessionBean();
        try {
            List<DeliveryBranch> deliveryBranches = sdls.getFNBPCDeliveryBranches();
            assertNotNull(deliveryBranches);
            assertTrue(deliveryBranches.size() > 0);
            for (Iterator<DeliveryBranch> iterator = deliveryBranches.iterator(); iterator.hasNext();) {
                DeliveryBranch deliveryBranch = iterator.next();
                System.err.println(deliveryBranch.getBranchNumber() + " - " + deliveryBranch.getBranchName());
                DeliveryBranchDetails branchDetails = sdls.getFNBPCDeliveryBranchDetails(deliveryBranch.getBranchNumber());
                assertNotNull(branchDetails);
                assertTrue(branchDetails.getAddressLine1().length() > 0);
                System.err.println(branchDetails.getAddressLine1());
                System.err.println(branchDetails.getAddressLine2());
                System.err.println(branchDetails.getAddressLine3());
                System.err.println(branchDetails.getBranchName());
                System.err.println(branchDetails.getBranchNumber());
                System.err.println(branchDetails.getFaxNumber());
                System.err.println(branchDetails.getTelephoneNumber());
                System.err.println();
            }
        } catch (ServerException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public void testRMBPBBranchLookups() {
        StaticDataLookupSession sdls = new StaticDataLookupSessionBean();
        try {
            List<DeliveryBranch> deliveryBranches = sdls.getRMBPBDeliveryBranches();
            assertNotNull(deliveryBranches);
            assertTrue(deliveryBranches.size() > 0);
            for (Iterator<DeliveryBranch> iterator = deliveryBranches.iterator(); iterator.hasNext();) {
                DeliveryBranch deliveryBranch = iterator.next();
                System.err.println(deliveryBranch.getBranchNumber() + " - " + deliveryBranch.getBranchName());
                DeliveryBranchDetails branchDetails = sdls.getRMBPBDeliveryBranchDetails(deliveryBranch.getBranchNumber());
                assertNotNull(branchDetails);
                assertTrue(branchDetails.getAddressLine1().length() > 0);
                System.err.println(branchDetails.getAddressLine1());
                System.err.println(branchDetails.getAddressLine2());
                System.err.println(branchDetails.getAddressLine3());
                System.err.println(branchDetails.getBranchName());
                System.err.println(branchDetails.getBranchNumber());
                System.err.println(branchDetails.getFaxNumber());
                System.err.println(branchDetails.getTelephoneNumber());
                System.err.println();
            }
        } catch (ServerException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public void testTitleLookups() {
        StaticDataLookupSession sdls = new StaticDataLookupSessionBean();
        try {
            List<Title> titles = sdls.getTitles();
            assertNotNull(titles);
            assertTrue(titles.size() > 0);
            for (Iterator<Title> iterator = titles.iterator(); iterator.hasNext();) {
                Title title = iterator.next();
                System.err.println(title.getTitle());
            }
        } catch (ServerException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public static void main(String[] args) {
        StaticDataLookupSession sdls = StaticDataServiceLocator.getStaticDataLookupSession();
        try {
            System.err.println("FNBPC Branches");
            System.err.println("~~~~~~~~~~~~~~");
            List<DeliveryBranch> deliveryBranches = sdls.getFNBPCDeliveryBranches();
            for (Iterator<DeliveryBranch> iterator = deliveryBranches.iterator(); iterator.hasNext();) {
                DeliveryBranch deliveryBranch = iterator.next();
                System.err.println(deliveryBranch.getBranchNumber() + " - " + deliveryBranch.getBranchName());
                DeliveryBranchDetails branchDetails = sdls.getFNBPCDeliveryBranchDetails(deliveryBranch.getBranchNumber());
                System.err.println(branchDetails.getTelephoneNumber());
                System.err.println();
            }
            
            System.err.println("RMBPB Branches");
            System.err.println("~~~~~~~~~~~~~~");
            deliveryBranches = sdls.getRMBPBDeliveryBranches();
            for (Iterator<DeliveryBranch> iterator = deliveryBranches.iterator(); iterator.hasNext();) {
                DeliveryBranch deliveryBranch = iterator.next();
                System.err.println(deliveryBranch.getBranchNumber() + " - " + deliveryBranch.getBranchName());
                DeliveryBranchDetails branchDetails = sdls.getRMBPBDeliveryBranchDetails(deliveryBranch.getBranchNumber());
                System.err.println(branchDetails.getTelephoneNumber());
                System.err.println();
            }
            
            System.err.println("Titles");
            System.err.println("~~~~~~");
            List<Title> titles = StaticDataServiceLocator.getStaticDataLookupSession().getTitles();
            for (Iterator<Title> iterator = titles.iterator(); iterator.hasNext();) {
                Title title = iterator.next();
                System.err.println(title.getTitle());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
