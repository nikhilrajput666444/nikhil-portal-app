package controller;

import java.io.IOException;
import java.util.List;

import dao.EnrollDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/viewenrollment")
public class ViewEnrollmentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cid = req.getParameter("cid");
		int courseId=Integer.parseInt(cid);
		
		
		EnrollDao edao = new EnrollDao(ConnectionProvider.getDbConnection());
		List<User> listofEnrolledUsers = edao.getUserInfoBycourseId(courseId);
		
		
		req.setAttribute("allenrolleduser", listofEnrolledUsers);
		req.getRequestDispatcher("enrollment_list.jsp").forward(req, resp);
		
		
	}

}

