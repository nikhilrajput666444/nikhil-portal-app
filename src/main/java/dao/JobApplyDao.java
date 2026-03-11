package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ApplicantsData;
import model.Job;

public class JobApplyDao {

	private Connection conn;

	public JobApplyDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// apply job

	public boolean applyForJob(int userid, int jobid) {
		boolean applied = false;
		try {
			String query = "insert into jobapplication (userId,jobId) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setInt(2, jobid);

			int i = ps.executeUpdate();
			if (i > 0) {
				applied = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return applied;

	}

	// already applied
	public boolean checkAlreadyApplied(int userid, int jobid) {
		boolean exists = false;

		try {
			String query = "select * from jobapplication where userId=? and jobId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setInt(2, jobid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exists;
	}

	// get job applicants list count
	public List<Job> getJobApplicantsCount() {

		List<Job> jobsApplicants = new ArrayList();

		try {

			String query = "select j.jobid,j.title, count(ja.appid) AS totalapplicants from job j LEFT JOIN jobapplication ja"
					+ " ON j.jobid=ja.jobid GROUP BY j.jobid,j.title";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Job job = new Job();
				job.setTitle(rs.getString("title"));
				job.setTotalJobApplicants(rs.getInt("totalapplicants"));
                job.setJobid(rs.getInt("jobid"));
				jobsApplicants.add(job);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobsApplicants;
	}
	
	//get Applicants data from db
	public List<ApplicantsData> getApplicantsData(int jobId)
	{
		
		List<ApplicantsData> applicantsData = new ArrayList();
		try {

			String query = "select ja.appid ,u.userid , u.Firstname ,u.Lastname ,u.Email ,u.mobilenumber ,ja.appdate from jobapplication ja JOIN  user u"
					+ " On ja.userId=u.userid where ja.jobId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, jobId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ApplicantsData data=new ApplicantsData();
				data.setApplicatonId(rs.getInt("appid"));
				data.setEmail(rs.getString("Email"));
				data.setFullName(rs.getString("Firstname")+"  "+rs.getString("Lastname"));
				data.setMobno(rs.getString("mobilenumber"));
				data.setUserId(rs.getInt("userid"));
				data.setAppliedDate(rs.getTimestamp("appdate")+"");
				
				applicantsData.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicantsData;
		
		
	}
	
	

}

