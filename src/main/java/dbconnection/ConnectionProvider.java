package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    private static Connection conn;
    
    public static Connection getDbConnection(){
    	
    	try {
    		
    		if(conn==null){
    			
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","nikhil/2002");
    			System.out.println("conn created");
    		}
    		
    		
    		
    		
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return conn;
    	
    	
    }

	
    
}
