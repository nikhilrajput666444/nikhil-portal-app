package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {
	
	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	//save user deails at signup time 
	
	public boolean saveUser(User user) {
		boolean f= false;
		
		try {
			//generate quary for insert data  and dont include user id because it is auto increament 
			
			String query="insert into user(Firstname,Lastname,Email,role,Password,imagename,mobilenumber)  values(?,?,?,?,?,?,?)";
			
			// use prepared statement 
			
	             PreparedStatement ps=conn.prepareStatement(query);
	             
	             ps.setString(1, user.getUserfirstname());
	             ps.setString(2, user.getUserlastname());
	             ps.setString(3, user.getUserEmail());
	             ps.setString(4, user.getRole());
	             ps.setString(5, user.getPassword());
	             ps.setString(6, user.getUserimage());
	             ps.setLong(7, user.getMobilenumber());
			
			    int i = ps.executeUpdate();	
			    
			    
			    if(i==1) {
			    	
			    	f=true;
			    }
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
		
		
	}
	
	// check email is already present ar not 
	public boolean isEmailExists(String email) {
		
		boolean exists =false;
		
		try {
			String query = "select*from user where email=?";
			        PreparedStatement ps=   conn.prepareStatement(query);
			        ps.setString(1, email);
			      ResultSet rs=  ps.executeQuery();
			      
			      if(rs.next()) {
			    	  
			    	  exists=true;
			      }
			      
			      
			      
			      
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return exists;
	}
	
	// check email and password for login purpose 
	
	public User getUserByEmailAndPassword(String Email, String Password) {
		
		
		User user = null;
		try {
			String query = "select * from user where Email=? and Password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Email);
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// fetch all the data from result set and set the data to object
				int uid = rs.getInt("userid");
				String Firstname =	rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email1 = rs.getString("Email");
				String role = rs.getString("role");
				String img = rs.getString("imagename");
				String mobilenumber = rs.getString("mobilenumber");
				
				
				user = new User();
				user.setUserid(uid);
				user.setUserfirstname(Firstname);
				user.setUserlastname(Lastname);
				user.setUserEmail(Email1);
				user.setRole(role);
				user.setUserimage(img);
				user.setMobilenumber(uid);

				
				
				
				
				
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return user;
		
		
	}
	
	
	
	
	
	

}
