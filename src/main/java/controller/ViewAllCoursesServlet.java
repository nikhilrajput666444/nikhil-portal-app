package controller;

import java.io.IOException;
import java.util.List;

import dao.CourseDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

@WebServlet("/viewcourses")
public class ViewAllCoursesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CourseDao cdao = new CourseDao(ConnectionProvider.getDbConnection());

		List<Course> allCourses;

		String search = req.getParameter("search");
		String status = req.getParameter("status");
		String feeOrder = req.getParameter("fee");

		if (search != null && !search.trim().isEmpty()) {
			allCourses = cdao.getCourseByName(search);
		} else if (status != null && !status.isEmpty()) {

			allCourses = cdao.getCourseByStatus(status);

		} 
		else if (feeOrder != null && !feeOrder.isEmpty()) {

			allCourses = cdao.getCourseByFee(feeOrder);

		} 
		else {
			allCourses = cdao.getAllCourses();
		}

		req.setAttribute("courses", allCourses);

		RequestDispatcher rd = req.getRequestDispatcher("allcourse.jsp");
		rd.forward(req, resp);

	}

}

