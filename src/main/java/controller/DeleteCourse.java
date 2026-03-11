package controller;

import java.io.IOException;

import dao.CourseDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteCourse extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String courseId = req.getParameter("cid");

		// create dao object
		CourseDao cdao = new CourseDao(ConnectionProvider.getDbConnection());
		
		boolean isDeleted = cdao.deleteCourseById(Integer.parseInt(courseId));
		
		// get session object
				HttpSession session = req.getSession();
		
		if(isDeleted)
		{
			resp.sendRedirect("viewcourse.jsp");
		}else
		{
			session.setAttribute("errormsg", "unable to delete something went wrong!!");
			resp.sendRedirect("viewcourse.jsp");
		}

	}

}
