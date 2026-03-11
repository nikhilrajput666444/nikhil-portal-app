package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class EnrollDao {

	private Connection conn;

	public EnrollDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// to save enrollment details
	public boolean enrollUser(int userId, int courseId) {
		boolean isEnrolled = false;

		try {
			String query = "insert into enrollment (myuserid,mycourseid) values (?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, courseId);

			int i = ps.executeUpdate();

			isEnrolled = i > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isEnrolled;

	}

	// is already enrolled
	public boolean isUserAlreadyEnrolled(int userId, int courseId) {
		boolean isAlreadyEnrolled = false;
		try {
			String query = "select * from enrollment where myuserid=? and mycourseid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, courseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				isAlreadyEnrolled = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAlreadyEnrolled;

	}

	// get course name based on id
	public String getCourseNameById(int courseId) {
		String courseName = "";
		try {
			String query = "select coursename from course where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				courseName = rs.getString("coursename");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseName;
	}

	// get enrollment details by using courseId and by joining user and course table

	public List<User> getUserInfoBycourseId(int courseId) {
		List<User> enrolledUsers = new ArrayList<>();
		try {
			String query = "select u.userid , u.Firstname,u.Email,u.mobilenumber from user "
					+ "u JOIN enrollment e ON u.userid=e.myuserid " 
					+ "where e.mycourseid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUserfirstname(rs.getString("Firstname"));
				user.setUserEmail(rs.getString("Email"));
				user.setMobilenumber(rs.getLong("mobilenumber"));
				

				enrolledUsers.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return enrolledUsers;

	}

}
