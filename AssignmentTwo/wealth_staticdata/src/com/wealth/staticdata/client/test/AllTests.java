package com.wealth.staticdata.client.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.wealth.constants.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(AccountTypesCreateTest.class);
		suite.addTestSuite(PropertyTypeCreateTest.class);
		suite.addTestSuite(ContactTypeCreateTest.class);
		//$JUnit-END$
		return suite;
	}

//	public static void main(String[] args) {
//		ClientCriteria surnameCriteria = new ClientCriteria();
//		surnameCriteria.setSurname("smi*");
//		
//		ClientCriteria statusCriteria = new ClientCriteria();
//		statusCriteria.setClientStatus("1");
//		
//		ClientTO newClientTO = new ClientTO();
//		newClientTO.setClientStatus(ClientStatus.Natural);
//		newClientTO.setCpysurname("scott");
//		newClientTO.setFirstname("paul");
//		newClientTO.setHoganAccountNumber("123123");
//		newClientTO.setPhoenixAccountNumber("3534534");
//		newClientTO.setPhoneNumber("0112345432");
//		newClientTO.setRegnumber("72727172635");
//		newClientTO.setRegtype("ID");
//		
//		ClientServiceImpl service = new ClientServiceImpl();
//		try {
//			ClientTO[] clients = service.findClients(surnameCriteria);
//			System.out.println("SURNAME: clients found: "+clients.length);
//			for (ClientTO clientTO : clients) {
//				System.out.println(clientTO.toString());
//			}
//			
//			clients = service.findClients(statusCriteria);
//			System.out.println("STATUS: clients found: "+clients.length);
//			for (ClientTO clientTO : clients) {
//				System.out.println(clientTO.toString());
//			}
//			
//			service.createClient(newClientTO);
//			System.out.println("new client created: "+newClientTO.toString());
//			
//		} catch (ServiceAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
