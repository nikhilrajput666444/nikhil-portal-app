package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import model.Course;

public class CourseDao {

	private Connection conn;

	public CourseDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	// to add new course
	public boolean saveCourse(Course course) 
	{
		boolean f = false;
		
		try {
		String query = "insert into course (coursename,courseduration,coursefee,status,cdesc,courseimage,pdfname,userid) values (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, course.getCourseName());
		ps.setString(2, course.getCourseDuration());
		ps.setInt(3, course.getCourseFee());
		ps.setString(4, course.getStatus());
		ps.setString(5, course.getCourseDesc());
		ps.setString(6, course.getCourseImg());
		ps.setString(7, course.getPdfName());
		ps.setInt(8, course.getUserId());

		int i = ps.executeUpdate();

		if (i == 1) {
			f = true;
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
		
		
	}
	
	// to get all courses
	public List<Course> getAllCourse() {
		List<Course> coursesList = new ArrayList<Course>();// empty collection to store course objects

		try {
			String query = "select * from course";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// create course object
				Course c = new Course();
				c.setCourseId(rs.getInt("cid"));
				c.setCourseDesc(rs.getString("cdesc"));
				c.setCourseDuration(rs.getString("courseduration"));
				c.setCourseFee(rs.getInt("coursefee"));
				c.setCourseImg(rs.getString("courseimage"));
				c.setCourseName(rs.getString("coursename"));
				c.setPdfName(rs.getString("pdfname"));
				c.setStatus(rs.getString("status"));
				c.setUserId(rs.getInt("userid"));

				coursesList.add(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return coursesList;

	}
	
	
	// get course based on course id from db
		public Course getCourseById(int courseId) {
			Course course = null;

			try {
				String query = "select * from course where cid=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, courseId);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					course = new Course();
					course.setCourseId(rs.getInt("cid"));
					course.setCourseName(rs.getString("coursename"));
					course.setCourseDuration(rs.getString("courseduration"));
					course.setCourseDesc(rs.getString("cdesc"));
					course.setCourseFee(rs.getInt("coursefee"));
					course.setCourseImg(rs.getString("courseimage"));
					course.setPdfName(rs.getString("pdfname"));
					course.setStatus(rs.getString("status"));

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return course;
		}
		// for update course
		public boolean updateCourse(Course course) {

			boolean updated = false;

			try {

				String query = "update course set coursename=? , courseduration=?,coursefee=?,status=?,cdesc=?,courseimage=?,pdfname=? where cid=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, course.getCourseName());
				ps.setString(2, course.getCourseDuration());
				ps.setInt(3, course.getCourseFee());
				ps.setString(4, course.getStatus());
				ps.setString(5, course.getCourseDesc());
				ps.setString(6, course.getCourseImg());
				ps.setString(7, course.getPdfName());
				ps.setInt(8, course.getCourseId());

				int i = ps.executeUpdate();
				if (i > 0) {
					updated = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return updated;

		}
		// delete course
		public boolean deleteCourseById(int id) {
			boolean isDelete = false;
			try {
				String query = "delete from course where cid=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i == 1) {
					isDelete = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return isDelete;
		}
		// get latest 6 course
		public List<Course> getLatestCourses() {

			List<Course> latestCourses = new ArrayList<Course>();// empty collection to store course objects
			try {

				String query = "select cid,coursename,coursefee,status,cdesc,courseimage  from course order by cid desc limit 6 ";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					// create course object
					Course c = new Course();
					c.setCourseId(rs.getInt("cid"));
					c.setCourseDesc(rs.getString("cdesc"));
					c.setCourseFee(rs.getInt("coursefee"));
					c.setCourseImg(rs.getString("courseimage"));
					c.setCourseName(rs.getString("coursename"));
					c.setStatus(rs.getString("status"));

					latestCourses.add(c);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return latestCourses;

		}
		// get all courses
		// get latest 6 course
		public List<Course> getAllCourses() {

			List<Course> latestCourses = new ArrayList<Course>();// empty collection to store course objects
			try {

				String query = "select cid,coursename,coursefee,status,cdesc,courseimage  from course order by cid desc";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					// create course object
					Course c = new Course();
					c.setCourseId(rs.getInt("cid"));
					c.setCourseDesc(rs.getString("cdesc"));
					c.setCourseFee(rs.getInt("coursefee"));
					c.setCourseImg(rs.getString("courseimage"));
					c.setCourseName(rs.getString("coursename"));
					c.setStatus(rs.getString("status"));

					latestCourses.add(c);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return latestCourses;

		}
		
		//-----------------------------------------
		
		
		// get course pdf name based on course id

		public String getCoursePdfName(int id) {
			String pdfName = null;

			try {
				String query = "select pdfname from course where cid=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					pdfName = rs.getString("pdfname");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return pdfName;
		}

		// get distinct status for drop down value
		public List<String> getUniqueStatus() {

			List<String> statusList = new ArrayList<String>();// empty collection to store course objects

			try {
				String query = "select distinct status from course";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					statusList.add(rs.getString("status"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return statusList;
		}

		// search by course name
		public List<Course> getCourseByName(String name) {

			List<Course> courses = new ArrayList<Course>();
			try {
				String query = "select * from course where coursename like ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, "%" + name + "%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// create course object
					Course c = new Course();
					c.setCourseId(rs.getInt("cid"));
					c.setCourseDesc(rs.getString("cdesc"));
					c.setCourseFee(rs.getInt("coursefee"));
					c.setCourseImg(rs.getString("courseimage"));
					c.setCourseName(rs.getString("coursename"));
					c.setStatus(rs.getString("status"));

					courses.add(c);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return courses;

		}

		// search by course status

		public List<Course> getCourseByStatus(String status) {
			List<Course> courses = new ArrayList<Course>();
			try {
				String query = "select * from course where status=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, status);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// create course object
					Course c = new Course();
					c.setCourseId(rs.getInt("cid"));
					c.setCourseDesc(rs.getString("cdesc"));
					c.setCourseFee(rs.getInt("coursefee"));
					c.setCourseImg(rs.getString("courseimage"));
					c.setCourseName(rs.getString("coursename"));
					c.setStatus(rs.getString("status"));

					courses.add(c);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return courses;

		}

		// search by course fee
		// low-hight-->ASC
		// high-low--->desc
		public List<Course> getCourseByFee(String order) {

			List<Course> courses = new ArrayList<Course>();

			// default sorting order
			String sortOrder = "ASC";

			if (order.equals("high-low")) {
				sortOrder = "DESC";
			}

			try {
				String query = "select * from course order by coursefee " + sortOrder;
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// create course object
					Course c = new Course();
					c.setCourseId(rs.getInt("cid"));
					c.setCourseDesc(rs.getString("cdesc"));
					c.setCourseFee(rs.getInt("coursefee"));
					c.setCourseImg(rs.getString("courseimage"));
					c.setCourseName(rs.getString("coursename"));
					c.setStatus(rs.getString("status"));

					courses.add(c);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return courses;
		}	
	
}
