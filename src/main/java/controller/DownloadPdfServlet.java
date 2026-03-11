package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import dao.CourseDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/download")
public class DownloadPdfServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get course id from req
		String cid = req.getParameter("cid");

		// get session object
		HttpSession session = req.getSession();

		CourseDao cdao = new CourseDao(ConnectionProvider.getDbConnection());
		String pdfName = cdao.getCoursePdfName(Integer.parseInt(cid));

		if (pdfName != null) {

			// pdf path
			String pdfDirlocation = getServletContext().getRealPath("/course_pdf");

			// pdf full path with pdf name
			String fullpath = pdfDirlocation + File.separator + pdfName;

			// check pdf File exists or not in that location
			File file = new File(fullpath);

			if (!file.exists()) {
				session.setAttribute("msg", "No File Found Please Contact with admin !! ");
				resp.sendRedirect("allcourse.jsp");
				return;
			}

			// download the pdf
			resp.setContentType("application/pdf");

			// to download pdf as a atachment set header
			resp.setHeader("Content-Disposition", "attachment; filename=" + pdfName);

			// read and wrtie pdf operation

			try (FileInputStream fis = new FileInputStream(file); ServletOutputStream sos = resp.getOutputStream();) {

				byte[] buffer = new byte[8192];// 8k buffer empty array

				int byteRead;

				while ((byteRead = fis.read(buffer)) != -1) {
					
					sos.write(buffer, 0, byteRead);
					sos.flush();

				}

				
//				int size = fis.available();
//				byte[] buffer = new byte[size];
//				
//				sos.write(buffer);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}

		} //

	}

}