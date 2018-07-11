package com.wealth.testing.csv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wealth.testing.csv.CSVReaderHelper;

public class SetupTestingDBHelper {
    
    public static final char CSV_SEPARATOR_CHAR = ','; 
    public static final char CSV_QUOTE_CHAR = '\'';
    
    public static boolean clearTestingDatabase(Connection conn, String[] deleteStatements) throws SQLException {
        boolean success = true;
        Statement stmt = null;
        try {
            if (!conn.isClosed()) {
                stmt = conn.createStatement();
                for (int i=0; (i<deleteStatements.length && success); i++) {                    
                    success = (stmt.executeUpdate(deleteStatements[i]) > -1);                
                }
            } else { success = false; }
        } finally {
            if (stmt != null) { stmt.close(); }
            //if (conn != null) { conn.commit(); }
        }        
        return success;
    }
    
    public static boolean setupTestDatabase(Connection conn, String[] insertStatements) throws SQLException, IOException {
        boolean success = true;
        Statement stmt = null;
        try {
            if (!conn.isClosed()) {
                stmt = conn.createStatement();
                
                for (int file=0; file<insertStatements.length; file++) {
                    String tableName = insertStatements[file]; 
                    String csvFile =  tableName + ".csv";
                    List<String[]> dataList = CSVReaderHelper.readFileFromClasspath(csvFile,
                            SetupTestingDBHelper.CSV_SEPARATOR_CHAR/*, SetupTestingDBHelper.CSV_QUOTE_CHAR*/);
                    
                    if ((dataList != null) && (dataList.size() > 1)) {
                        String[] columns = dataList.get(0);
                        
                        for (int data=1; (data<dataList.size() && success); data++) {
                            String[] values = dataList.get(data);
                            if (values.length == columns.length) {
                                String sql = SetupTestingDBHelper.buildInsertStatement(
                                        insertStatements[file], columns, values);
                                success = (stmt.executeUpdate(sql) > -1);
                            }
                        }
                    }
                }    
            } else { success = false; }
        } finally {
            if (stmt != null) { stmt.close(); }
            //if (conn != null) { conn.commit(); }
        }        
        return success;
    }
    
    private static String buildInsertStatement(String table, String[] columns, String[] values) {
        StringBuffer result = new StringBuffer();
        result.append("insert into ").append(table).append(" (");
        for (int c=0; c<columns.length; c++) {
            if (c == (columns.length-1)) {
                result.append(columns[c]).append(")");
            } else {
                result.append(columns[c]).append(",");
            }
        }
        result.append(" values (");
        for (int v=0; v<values.length; v++) {
            String val = "".equals(values[v]) ? "null" : values[v];
            if (v == (values.length-1)) {
                result.append(val).append(");");
            } else {
                result.append(val).append(",");
            }
        }
        return result.toString();
    }
    
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://freakentwig:3306/wealth_testing?" + 
                                            "user=testing&password=testing");            
            boolean success = SetupTestingDBHelper.clearTestingDatabase(conn, new String[] {});
            System.out.println("done...["+success+"]");
            success = SetupTestingDBHelper.setupTestDatabase(conn, new String[] {});
            System.out.println("done...["+success+"]");
        }
        catch (SQLException e) {            
            e.printStackTrace();
        }
        catch (InstantiationException e) {            
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {            
            e.printStackTrace();
        }
    }
}