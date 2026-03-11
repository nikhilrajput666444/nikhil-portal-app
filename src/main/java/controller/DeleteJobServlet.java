package controller;

import java.io.IOException;

import dao.JobDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deletejob")
public class DeleteJobServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jobId = req.getParameter("jid");
		
		// get session object
		HttpSession session = req.getSession();
		
		JobDao jdao=new JobDao(ConnectionProvider.getDbConnection());
		boolean isDeleted = jdao.deleteJobById(Integer.parseInt(jobId));
		
		if(isDeleted)
		{
			session.setAttribute("successmsg", "deleted!!");
			resp.sendRedirect("viewalljobs.jsp");
		}else
		{
			session.setAttribute("errormsg", "  some thing went wrong!!");
			resp.sendRedirect("viewalljobs.jsp");
		}
		
		
	}
	
	
	
	

}

