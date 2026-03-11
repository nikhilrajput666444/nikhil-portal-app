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
import model.Job;

@WebServlet("/addjob")
public class AddJobController  extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		
		String location = req.getParameter("location");
		
		String category = req.getParameter("category");
		
		String status = req.getParameter("status");
		
		String jobdesc = req.getParameter("jobdesc");
		
		
		//create job object and fill this data
		Job job=new Job();
		job.setTitle(title);
		job.setStatus(status);
		job.setCategory(category);
		job.setLocation(location);
	   job.setJobdesc(jobdesc);
	   
	   //create jobao object
	   JobDao jdao=new JobDao(ConnectionProvider.getDbConnection());
	   boolean isJobPost = jdao.addJob(job);
		
	   //get session object
		HttpSession session = req.getSession();
		
		if(isJobPost)
		{
			session.setAttribute("successmsg", "job posted !!");
			resp.sendRedirect("addjobs.jsp");
		}else
		{
			session.setAttribute("errormsg", "something went wrong !!");
			resp.sendRedirect("addjobs.jsp");
		}
		
		
		
	}
	
	
	
	
	
}
