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

@WebServlet("/updatejob")
public class UpdateJobServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");

		String location = req.getParameter("location");

		String category = req.getParameter("category");

		String status = req.getParameter("status");

		String jobdesc = req.getParameter("jobdesc");
		
		String jobId = req.getParameter("jobid");

		// create job object and fill this data
		Job job = new Job();
		job.setTitle(title);
		job.setStatus(status);
		job.setCategory(category);
		job.setLocation(location);
		job.setJobdesc(jobdesc);
		job.setJobid(Integer.parseInt(jobId));
		
		//create dao object
		 JobDao jdao=new JobDao(ConnectionProvider.getDbConnection());
		 boolean isUpdated = jdao.updateJob(job);
		 
		 HttpSession session = req.getSession();
		 
		 if(isUpdated)
		 {
			 session.setAttribute("successmsg", "job updated !!! ");
			 resp.sendRedirect("viewalljobs.jsp");
		 }else
		 {
			 session.setAttribute("errormsg", "something went wrong !!! ");
			 resp.sendRedirect("viewalljobs.jsp");
		 }
	}

}

