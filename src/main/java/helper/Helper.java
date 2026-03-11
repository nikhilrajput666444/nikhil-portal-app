package helper;

import java.io.File;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;

public class Helper {

	public static boolean saveFile(Part part, String uploadDir, String filename) {

		boolean f = false;

		try {
			// ensure uploads directory exists or not
			File dir = new File(uploadDir);

			if (!dir.exists()) {

				dir.mkdir();// create a folder

			}

			// construct full file path
			// :- /profile_img/card5.png
			String filePath = uploadDir + File.separator + filename;
			System.out.println(filePath);

			// save file to folder
			part.write(filePath);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return f;

	}//get profile image
	public static String getUserProfileImage(HttpSession session)
	{
		// get real path of upload path image directory
	            String uploadedPath =	session.getServletContext().getRealPath("profile_img");
		
		//get logged in user from session
	          User user =(User)session.getAttribute("userobj");
	            
	          if (user!=null && user.getUserimage()!=null  && !user.getUserimage().isEmpty()) {
				
	        	    String imagePath=  uploadedPath+File.separator+user.getUserimage();   
	        	    
	        	   File f = new File(imagePath);
	        	    
	        	  if (f.exists()) {
					return user.getUserimage();
				}
	        	  
			}
	          
	          return "default.png";
		
	}
    
}
