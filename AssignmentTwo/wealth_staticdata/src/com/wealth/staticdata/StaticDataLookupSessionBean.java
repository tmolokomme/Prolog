package com.wealth.staticdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.account.AccountTypeServiceImpl;
import com.wealth.staticdata.client.ACBBankBranchInfo;
import com.wealth.staticdata.client.DeliveryBranch;
import com.wealth.staticdata.client.DeliveryBranchDetails;
import com.wealth.staticdata.client.StaticDataLookupSession;
import com.wealth.staticdata.client.Title;
import com.wealth.staticdata.client.transferobjects.AccountTypeTO;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;
import com.wealth.staticdata.client.transferobjects.ProductHouseTO;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;
import com.wealth.staticdata.contact.ContactTypeServiceImpl;
import com.wealth.staticdata.producthouse.ProductHouseServiceImpl;
import com.wealth.staticdata.property.PropertyTypeServiceImpl;

@Stateless
@WebService
public class StaticDataLookupSessionBean
	implements StaticDataLookupSession {
	
	private static final String ABSA_CODE = "632005";
	private static final String FNB_CODE = "250655";
	//private static final String NEDBANK_CODE = "";
	private static final String STDBNK_CODE = "051001";

    private static final String CODE = "BRANCH_NO";
    private static final String NUMBER = "FNB_HOGAN_BRANCHNUMBER";
    private static final String NAME = "BRANCH_NAME";
    private static final String ADDRESS1 = "ADDRESS_LINE1";
    private static final String ADDRESS2 = "ADDRESS_LINE2";
    private static final String ADDRESS3 = "ADDRESS_LINE3";
    private static final String TELEPHONE = "TELEPHONE";
    private static final String FAX = "FAX";
    private static final String TITLE = "TITLE";
        
    //private static final String STATIC_DATA_DATASOURCE_NAME = "wealth/ds/StaticData_DS";
    private static final String FNBPC_BRANCH_TABLE = "FNBPC_BRANCHES";
    private static final String RMBPB_BRANCH_TABLE = "RMBPB_BRANCHES";
    private static final String GET_TITLES = "SELECT " + TITLE + " FROM Titles ORDER BY "+ TITLE + " ASC";
    
    private static final AccountTypeServiceImpl accTypesService = new AccountTypeServiceImpl();
    private static final ContactTypeServiceImpl contactTypesService = new ContactTypeServiceImpl();
    private static final PropertyTypeServiceImpl propTypesService = new PropertyTypeServiceImpl();
    private static final ProductHouseServiceImpl prodHouseService= new ProductHouseServiceImpl();
    
    
    private String getDeliveryBranchDetailsQuery(String tableName, String branchNumber) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT ").append(NUMBER).append(", ").append(CODE).append(", ").append(NAME).append(", ").append(ADDRESS1).append(", ");
        query.append(ADDRESS2).append(", ").append(ADDRESS3).append(", ").append(TELEPHONE).append(", ").append(FAX);
        query.append(" FROM ").append(tableName).append(" WHERE ").append(NUMBER).append("=").append(branchNumber);
        
        return query.toString();
    }
    
    private String getDeliveryBranches(String tableName) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT ").append(NUMBER).append(", ").append(NAME).append(" FROM ").append(tableName);

        return query.toString();
    }

    @WebMethod
    public AccountTypeTO[] getAllActiveAccountTypes(){
    	return accTypesService.getActiveAccountTypes();
    }
    
    @WebMethod
    public ContactTypeTO[] getAllActiveContactTypes(){
    	return contactTypesService.getActiveContactTypes();
    }

    @WebMethod
    public PropertyTypeTO[] getAllActivePropertyTypes(){
    	return propTypesService.getActivePropertyTypes();
    }  
    
    @WebMethod
    public ProductHouseTO[] getProductHouseDetails(){
    	return prodHouseService.getProductHouseDetails();
    }
    
    @WebMethod
    public ProductHouseTO getProductHouseDetailsById(int id){
    	return prodHouseService.getProductHouseDetailsById(id);
    }
    
    public DeliveryBranchDetails getFNBPCDeliveryBranchDetails(final String branchNumber) throws ServerException {
        DeliveryBranchDetails branchDetails = new DeliveryBranchDetails();
        try {
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
                connection = getConnection(session); //session.connection();
                                     
                Statement statement = connection.createStatement();
                statement.execute(getDeliveryBranchDetailsQuery(FNBPC_BRANCH_TABLE, branchNumber));
                resultSet = statement.getResultSet();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        branchDetails = getDeliveryBranchDetails(resultSet);
                    } else {
                        throw new ServerException("No Delivery Branch Details found for Branch Number specified.");
                    }
                } else {
                    throw new ServerException("No Delivery Branch Details found for Branch Number specified.");
                }
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        
        return branchDetails;
    }

    public DeliveryBranchDetails getRMBPBDeliveryBranchDetails(String branchNumber) throws ServerException {
        DeliveryBranchDetails branchDetails = new DeliveryBranchDetails();
        
        try {
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
                connection = getConnection(session); //session.connection();
                Statement statement = connection.createStatement();
                statement.execute(getDeliveryBranchDetailsQuery(RMBPB_BRANCH_TABLE, branchNumber));
                resultSet = statement.getResultSet();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        branchDetails = getDeliveryBranchDetails(resultSet);
                    } else {
                        throw new ServerException("No Delivery Branch Details found for Branch Number specified.");
                    }
                } else {
                    throw new ServerException("No Delivery Branch Details found for Branch Number specified.");
                }
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        
        return branchDetails;
    }

    private DeliveryBranchDetails getDeliveryBranchDetails(ResultSet resultSet) throws SQLException {
        DeliveryBranchDetails branchDetails = new DeliveryBranchDetails();
        branchDetails.setAddressLine1(resultSet.getString(ADDRESS1));
        branchDetails.setAddressLine2(resultSet.getString(ADDRESS2));
        branchDetails.setAddressLine3(resultSet.getString(ADDRESS3));
        branchDetails.setBranchName(resultSet.getString(NAME));
        branchDetails.setBranchNumber(resultSet.getString(NUMBER));
        branchDetails.setBranchCode(resultSet.getString(CODE));
        branchDetails.setFaxNumber(resultSet.getString(FAX));
        branchDetails.setTelephoneNumber(resultSet.getString(TELEPHONE));
        
        return branchDetails;
    }

    public List<DeliveryBranch> getFNBPCDeliveryBranches() throws ServerException {
        List<DeliveryBranch> branchList = new ArrayList<DeliveryBranch>();
        
        try {
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
                connection = getConnection(session); //session.connection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(getDeliveryBranches(FNBPC_BRANCH_TABLE));
//                resultSet = statement.getResultSet();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        branchList = getDeliveryBranches(resultSet);
                    } else {
                        throw new ServerException("No Delivery Branches found.");
                    }
                } else {
                    throw new ServerException("No Delivery Branches found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServerException(e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        
        return branchList;
    }

    public List<DeliveryBranch> getRMBPBDeliveryBranches() throws ServerException {
        List<DeliveryBranch> branchList = new ArrayList<DeliveryBranch>();
        
        try {
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
                connection = getConnection(session); //session.connection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(getDeliveryBranches(RMBPB_BRANCH_TABLE));
//                resultSet = statement.getResultSet();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        branchList = getDeliveryBranches(resultSet);
                    } else {
                        throw new ServerException("No Delivery Branches found.");
                    }
                } else {
                    throw new ServerException("No Delivery Branches found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServerException(e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        
        return branchList;
    }

    private List<DeliveryBranch> getDeliveryBranches(ResultSet resultSet) throws SQLException {
        List<DeliveryBranch> branchList = new ArrayList<DeliveryBranch>();
        
        do {
            DeliveryBranch deliveryBranch = new DeliveryBranch();
            deliveryBranch.setBranchName(resultSet.getString(NAME));
            deliveryBranch.setBranchNumber(resultSet.getString(NUMBER));
            branchList.add(deliveryBranch);
        } while (resultSet.next());
        
        return branchList;
    }
    
    /*
     * (non-Javadoc)
     * @see com.wealth.staticdata.client.StaticDataLookupSession#getTitles()
     */
    @WebMethod
    public List<Title> getTitles() throws ServerException {
        List<Title> titleList = new ArrayList<Title>();
        
        try {
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
                connection = getConnection(session); //session.connection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(GET_TITLES);
//                resultSet = statement.getResultSet();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        titleList = getTitles(resultSet);
                    } else {
                        throw new ServerException("No Titles found.");
                    }
                } else {
                    throw new ServerException("No Titles found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServerException(e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        
        return titleList;
    }
    
    private List<Title> getTitles(ResultSet resultSet) throws SQLException {
        List<Title> titleList = new ArrayList<Title>();
        
        do {
            Title title = new Title();
            title.setTitle(resultSet.getString(TITLE));
            titleList.add(title);
            
        } while (resultSet.next());
        
        return titleList;
    }

    //done at a blistering pace for payments automation by carel fourie
    @WebMethod
    public ACBBankBranchInfo[] getBanks() throws ServerException {
    	String sql  = "select distinct (BankName) from ACBBANK";
    	return getBankBranchInfo(sql);	
	}

    @WebMethod
	public ACBBankBranchInfo[] getBranchesForBank(String bankName) throws ServerException {
    	String sql = "select * from ACBBANK where BankName like '" + bankName + "'";
		return getBankBranchInfo(sql);		
	}    
    
    @WebMethod
	public ACBBankBranchInfo getBranchInfo(String branchCode) throws ServerException {
    	String sql = "select * from ACBBANK where BranchCode = '" + branchCode + "'";
    	ACBBankBranchInfo[] acb = getBankBranchInfo(sql);
		return acb[0]; 		
	}
	
	@SuppressWarnings("deprecation")
	private ACBBankBranchInfo[] getBankBranchInfo(String sql/*String branchCode*/) throws ServerException {
		Connection connection = null;
        try {        	        	
        	final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);            
        	connection = getConnection(session); //session.connection();
            Statement statement = connection.createStatement();        	
                       
            /*
            if (bankName == null) {       
            	resultSet = statement.executeQuery("select distinct(BankName) from ACBBANK");
            }
            else {
            	resultSet = statement.executeQuery("select distinct(BranchName) from ACBBANK where BankName like :bankName");
            }
            */            
          
            //String sql = "select * from ACBBANK where BranchCode = '" + branchCode + "'";
            ResultSet resultSet = statement.executeQuery(sql);            
            
            List<ACBBankBranchInfo> bnkBrnInfo = new ArrayList<ACBBankBranchInfo>();
            while (resultSet.next()) {            	
            	ACBBankBranchInfo acb = new ACBBankBranchInfo(resultSet.getString("BankName"),
            																				   resultSet.getString("BranchCode"),
            																				   resultSet.getString("BranchName"));
            	
            	//setDefaultBranchCodes(acb);  																				   
            	bnkBrnInfo.add(acb);            		            				
			}            
            return bnkBrnInfo.toArray(new ACBBankBranchInfo[bnkBrnInfo.size()]);	     
        }
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	private void setDefaultBranchCodes(ACBBankBranchInfo acb) {
		if (acb.getBankName().equalsIgnoreCase("standard bank")) {
			acb.setBranchCode(STDBNK_CODE);			
		}
		else if (acb.getBankName().equalsIgnoreCase("absa")) {
			acb.setBranchCode(ABSA_CODE);
			//ABSA ELECTRONIC SETTLEMENT CNT
		}
		else if (acb.getBankName().equalsIgnoreCase("firstrand bank")) {
			acb.setBranchCode(FNB_CODE);
		}
		/*
		else if (acb.getBankName().equalsIgnoreCase("nedbank")) {
			acb.setBranchCode(NEDBANK_CODE);
		}
		*/	
	}
	
	private static Connection getConnection(Session session) {
		SessionFactoryImplementor sessionFactoryImplementator = (SessionFactoryImplementor) session.getSessionFactory();
	    ConnectionProvider connectionProvider = sessionFactoryImplementator.getConnectionProvider();
		/*session.doWork(
			    new Work() {
			        public void execute(Connection connection) throws SQLException 
			        { 
			            PreparedStatement pStmt = null;
                           try
                           {
                                  String sqlQry = "UPDATE EMP_DETAILS set IS_ACTIVE='N' WHERE EMP_ID=?";
                                  pStmt = conn.prepareStatement(sqlQry);
                                  for(String empId:employeeList)
                                  {
                                         pStmt.setString(1, empId);
                                         pStmt.addBatch();
                                  }
                                  pStmt.executeBatch();
                           }
                           finally
                           {
                                  pStmt.close();
                           }                     
			        }
			    }
			);
			*/
	    Connection conn = null;
		try {
			conn = connectionProvider.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return conn;
		
	}
}




