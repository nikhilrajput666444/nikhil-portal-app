package helper;

import java.util.List;

import dao.CourseDao;
import dbconnection.ConnectionProvider;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListner implements ServletContextListener{



	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		CourseDao cdao=new CourseDao(ConnectionProvider.getDbConnection());
		
		List<String> statusList = cdao.getUniqueStatus();
		
		ServletContext ctx = sce.getServletContext();
		
		ctx.setAttribute("statuslist", statusList);
		System.out.println("status load from context object ");
	}
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	

}

