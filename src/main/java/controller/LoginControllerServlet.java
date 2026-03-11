package controller;

import java.io.IOException;

import dao.UserDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/login")
public class LoginControllerServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("Email");
		String pass = req.getParameter("Password");
		
		UserDao udao=new UserDao(ConnectionProvider.getDbConnection());
		User u = udao.getUserByEmailAndPassword(email, pass);
		
		//get session object
				HttpSession session = req.getSession();
		
		if(u!=null) {
			
			if(u.getRole().equals("admin")) {
				
				//when role is admin	
				//when role is normal
				session.setAttribute("userobj", u);
				session.setAttribute("role", u.getRole());
				//send the request to index page
				resp.sendRedirect("admin.jsp");
				
				
				
				
				
				
				
			}else {
				
				//when role is normal
				session.setAttribute("userobj", u);
				session.setAttribute("role", u.getRole());
				//send the request to index page
				resp.sendRedirect("index.jsp");
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
		}else {
			session.setAttribute("msg", "Invalid email or password");
			resp.sendRedirect("login.jsp");
		}
		
		
		
		
		
		
		
		
	}
	
	
}
