package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import dao.UserDao;
import dbconnection.ConnectionProvider;
import helper.Helper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;


@WebServlet ("/signup")
@MultipartConfig
public class SignUpControllerServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//get all forms feilds data
		String Firstname=req.getParameter("Firstname");
		String Lastname=req.getParameter("Lastname");
		String Email=req.getParameter("Email");
		String Password=req.getParameter("Password");
		String Mobilenumber=req.getParameter("Mobilenumber");
		
		//convert mobilenumber into string to long
		
		  long mobilenumber= Long.parseLong(Mobilenumber);
		  
		 //get submitted file from that part 
		  Part part = req.getPart("Pic");
		  
		  // getsubmitted file name form that pic part 
		  
		 String Picname = part.getSubmittedFileName();
		 
		 
		  // create dao object and call  save method 
         
         UserDao udao= new UserDao(ConnectionProvider.getDbConnection());
         
         //create session object
               HttpSession session =   req.getSession();         
         
         
		 
		 
		 
		 // check  weather user is already exists 
		                 //boolean emailExists= udao.isEmailExists(Email);
		 
		 if(udao.isEmailExists(Email)){
			 
			session.setAttribute("msg", "email already exists please try with another email !!!");
			resp.sendRedirect("signup.jsp");
			 return;
		 }
		 
		 
		 
			
			// working with image 
		 
		            String uploadPath= getServletContext().getRealPath("/profile_img");
		            
		            //change every profile name into unique name by timestamp and random number 
		            if(Picname!=null && !Picname.trim().isEmpty()) {
		            	
		                 String fileExtension =	Picname.substring(Picname.lastIndexOf("."));
		                 Picname=Picname+UUID.randomUUID().toString()+"_"+System.currentTimeMillis()+fileExtension;
		            	
		            }
		            
		            
		            
		            
		            
		            
		            // boolean isImageSaved=	Helper.saveFile(part, uploadPath, Picname);
			                 boolean isImageSaved= Helper.saveFile(part, uploadPath, Picname);
			                 
			                 //if image uploaded to directory sucessfully  then save user details to db 
			                 
			                 User u = new User();
			                 u.setUserfirstname(Firstname);
			                 u.setUserlastname(Lastname);
			                 u.setUserEmail(Email);
			                 u.setRole("normal");
			                 u.setPassword(Password);
			                 u.setUserimage(Picname);
			                 u.setMobilenumber(mobilenumber);
			                 
			               
			                 
			                 // check image is saved in directory or not 
			                  // boolean saveUser=    udao.saveUser(u);
			                 
			                 if(isImageSaved) {
			                	 
			                	 boolean isSaved=    udao.saveUser(u);
			                	 
			                	 if(isSaved) {
			                		 session.setAttribute("msg", "Sucess");
			                		 resp.sendRedirect("signup.jsp");
			                	 }else {
			                		 session.setAttribute("msg", "failed");
			                		 resp.sendRedirect("signup.jsp");
								}
			                	 
			                	 
			                 }else {
			                	 
			                	 
			                	 
			                 }
			                 
			                 
			                 
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
