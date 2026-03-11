package controller;

import java.io.IOException;
import java.util.UUID;

import dao.CourseDao;
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
import model.Course;

@WebServlet("/editcourse")
@MultipartConfig
public class EditControllerServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get all form fields data
				String status = req.getParameter("cstatus");
				String cname = req.getParameter("cname");
				String cdur = req.getParameter("cdur");
				String cdesc = req.getParameter("cdesc");
				String cfee = req.getParameter("cfee");// string

				String courseId = req.getParameter("courseid");
				
				// getting old exisitng image name
				String existingCourseImageName = req.getParameter("existingpic");

				// getting old existing pdf name
				String existingCoursePdfName = req.getParameter("existingpdf");

				// convert fee into int
				int courseFee = Integer.parseInt(cfee);

				// get latest course image part and get file name
				Part imagePart = req.getPart("cpic");
				String latestImageName = imagePart.getSubmittedFileName();

				// get latest pdf part and get pdf file name
				Part pdfPart = req.getPart("cpdf");
				String latestPdfName = pdfPart.getSubmittedFileName();

				// get session object
				HttpSession session = req.getSession();

				// prepare Course Object with latest updated value
				Course course = new Course();
				course.setCourseName(cname);
				course.setCourseDuration(cdur);
				course.setCourseDesc(cdesc);
				course.setCourseFee(courseFee);
				course.setStatus(status);
				course.setCourseId(Integer.parseInt(courseId));

				// handling latest image
				if (latestImageName != null && !latestImageName.trim().isEmpty()) {
					latestImageName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
							+ latestImageName.substring(latestImageName.lastIndexOf("."));

					// get uploaded dir
					String imageUploadedPAth = getServletContext().getRealPath("/course_img");

					boolean isImageSaved = Helper.saveFile(imagePart, imageUploadedPAth, latestImageName);

					if (isImageSaved) {
						course.setCourseImg(latestImageName);
					} else {
						course.setCourseImg(existingCourseImageName);
					}

				} else {
					course.setCourseImg(existingCourseImageName);
				}

				// handling latest pdf

				if (latestPdfName != null && !latestPdfName.trim().isEmpty()) {
					latestPdfName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
							+ latestPdfName.substring(latestPdfName.lastIndexOf("."));

					// get uploaded dir
					String pdfUploadedPAth = getServletContext().getRealPath("/course_pdf");

					boolean isPdfSaved = Helper.saveFile(pdfPart, pdfUploadedPAth, latestPdfName);

					if (isPdfSaved) {
						course.setPdfName(latestPdfName);
					} else {
						course.setPdfName(existingCoursePdfName);
					}

				} else {
					course.setPdfName(existingCoursePdfName);
				}

				// create dao object
				CourseDao cdao = new CourseDao(ConnectionProvider.getDbConnection());
				boolean isUpdated = cdao.updateCourse(course);

				if (isUpdated) {
					session.setAttribute("successmsg", "Course Updated !!!!!");
				} else {
					session.setAttribute("errormsg", "something went wrong !!!!!");
				}

				resp.sendRedirect("viewcourse.jsp");

				
				
				
				
	}
	
	
	
}
