package controller;

import java.io.IOException;
import java.sql.Connection;

import dao.JobApplyDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/applyjob")
public class ApplyJobServlet extends HttpServlet{
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jobId = req.getParameter("jid");
		int jobid = Integer.parseInt(jobId);
		
		//get session and from session check logged in user
		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("userobj");

		if (user == null) {
			session.setAttribute("successmsg", "Please login first !!!!");
			resp.sendRedirect("login.jsp");
			return;
		}
		
		
		JobApplyDao jad=new JobApplyDao(ConnectionProvider.getDbConnection());
		
		//first call dao method and check user id and joib id already exists
		boolean alreadyExisted = jad.checkAlreadyApplied(user.getUserid(), jobid);
		
		if(alreadyExisted)
		{
			session.setAttribute("errormsg", "Thanks for interest You Already Applied Before !!");
			resp.sendRedirect("viewalljobs.jsp");
		}else
		{
			

			boolean isapplied = jad.applyForJob(user.getUserid(), jobid);
			
			if(isapplied)
			{
				session.setAttribute("successmsg", "Thanks for applying !!");
				resp.sendRedirect("viewalljobs.jsp");
			}else
			{
				session.setAttribute("errormsg", "something went wrong contact admin !!");
				resp.sendRedirect("viewalljobs.jsp");
			}
			
		}
		
		
		

		
	}

}

