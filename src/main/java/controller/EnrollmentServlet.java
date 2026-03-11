package controller;

import java.io.IOException;

import dao.EnrollDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/enrollment")
public class EnrollmentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cid = req.getParameter("cid");
		int courseId = Integer.parseInt(cid);
		
//--------------------------- check user is logged in or not .. if not then redirect to login.jsp-----------------------
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userobj");
		
		if (user == null) {
			session.setAttribute("successmsg", "Please login first !!!!");
			resp.sendRedirect("login.jsp");
			return;
		}
		
		// enrollment logic
		
EnrollDao edao = new EnrollDao(ConnectionProvider.getDbConnection());
		
		
		
		String courseName = edao.getCourseNameById(courseId);

		if (edao.isUserAlreadyEnrolled(user.getUserid(), courseId)) {
			
			session.setAttribute("errormsg", "Already enrolled in This Course  "+courseName);
			resp.sendRedirect("viewcourses");
		} else {

			boolean isEnrolled = edao.enrollUser(user.getUserid(), courseId);

			if (isEnrolled) {
				
				
				session.setAttribute("successmsg", "Thanks for Enrolling in Our Course "+courseName+" !!");
			} else {
				session.setAttribute("errormsg", "Something went wrong  !!");
			}

			resp.sendRedirect("viewcourses");
		}
		
		
		
	}
	
	
	
	
	
}
