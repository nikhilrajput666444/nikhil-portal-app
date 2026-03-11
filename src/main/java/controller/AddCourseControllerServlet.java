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
import model.User;

@WebServlet("/addcourse")
@MultipartConfig
public class AddCourseControllerServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get all forms feild data 
		
		   String status = req.getParameter("cstatus");
		   String cname = req.getParameter("cname");
		   String cdur = req.getParameter("cdur");
		   String cdesc = req.getParameter("cdesc");
		   String cfee = req.getParameter("cfee");//string
		   
		   //convert fee into integer
		   
		     int courseFee =  Integer.parseInt(cfee);
		
	      //get course image part and get file name 
		          Part imagePart =  req.getPart("cpic");
		                  String imageName = imagePart.getSubmittedFileName();
		            
		                  
		  // get course pdf part and get pdf file name 
		        Part pdfPart =  req.getPart("cpdf");   
		        String pdfName =  pdfPart.getSubmittedFileName();
		     
		     
		    //get session object 
		        HttpSession session =  req.getSession();
		     
				 if((imageName ==null || imageName.trim().isEmpty()&&pdfName==null ||
				 pdfName.isBlank()))
				 {

				 session.setAttribute("imgmsg", "please select course image !!!");
				 session.setAttribute("pdfmsg", "please select course image !!!");
				 resp.sendRedirect("addcourse.jsp");
				 return;
				}
		        
		     // checking image and pdf uploaded or not
				// step 1:- check image uploads or not
		        
		       // boolean hasError = false;
		        
		       // if (imageName == null || imageName.trim().isEmpty()) {
				//	session.setAttribute("imgmsg", "Please select Course image !!!!");
				//	hasError = true;
			//	}
		        
		      //  if (pdfName == null || pdfName.trim().isEmpty()) {
				//	session.setAttribute("pdfimg", "Please select Course syllabus pdf !!!!");
				//	hasError = true;
			//	}
		        
		    //    if (hasError) {
			//		resp.sendRedirect("addcourse.jsp");
			//		return;
			//	}
		        
		        
		        
		    
		 // generate unique name for pdf/image using UUID + timetamp
			imageName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
					+ imageName.substring(imageName.lastIndexOf("."));

			pdfName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
					+ pdfName.substring(pdfName.lastIndexOf("."));
		    
		    
			// get real path of directory
			
			// course image path
			String imageUploadedPath = getServletContext().getRealPath("/course_img");
		   
			// course pdf path
			String pdfUploadedPath = getServletContext().getRealPath("/course_pdf");
		
			// save file to directory

			// save image
			boolean isImageSaved = Helper.saveFile(imagePart, imageUploadedPath, imageName);

			// save pdf
			boolean isPdfSaved = Helper.saveFile(pdfPart, pdfUploadedPath, pdfName);
			
			// create Course object
			
			if (isImageSaved && isPdfSaved)
			{
				
				// create Course object
				Course course = new Course();
				course.setCourseName(cname);
				course.setCourseDuration(cdur);
				course.setCourseDesc(cdesc);
				course.setCourseFee(courseFee);
				course.setCourseImg(imageName);
				course.setPdfName(pdfName);
				course.setStatus(status);
				
				// set user id
				// get user object from session object and get user id from there and set it to
				// course
				
				User user = (User) session.getAttribute("userobj");
				course.setUserId(user.getUserid());

				CourseDao cdao = new CourseDao(ConnectionProvider.getDbConnection());
				boolean isCourseSaved = cdao.saveCourse(course);
				
				if (isCourseSaved) {
					session.setAttribute("successmsg", "Course added !!!!");
					resp.sendRedirect("addcourse.jsp");
				} else {
					session.setAttribute("errormsg", "something went wrong !!!!");
					resp.sendRedirect("addcourse.jsp");
				}
				
				
				
			}else
			{
				
			}
			
			
			
			
		
	}
	
	
	
}
