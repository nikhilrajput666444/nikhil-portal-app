package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Job;

public class JobDao {

	private Connection conn;

	public JobDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// add job
	public boolean addJob(Job job) {
		boolean f = false;

		try {

			String query = "insert into job (title,jobdesc,cat,status,location) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getJobdesc());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());

			int i = ps.executeUpdate();
			if (i == 1)
				f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// to get all list of jobs

	public List<Job> getAllJobs(String role) {
		List<Job> jobList = new ArrayList<>();

		Job j = null;
		try {
			String query;
			// check user role and based on that fetch the jobs
			// if user is normal then fetch only active jobs
			// if user is admin then fetch all jobs

			if (role.equals("normal")) {
				query = "select * from job where status='active' order by jobid DESC";
			} else {
				query = "select * from job  order by jobid DESC";
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				j = new Job();

				j.setJobid(rs.getInt("jobid"));
				j.setTitle(rs.getString("title"));
				j.setJobdesc(rs.getString("jobdesc"));
				j.setCategory(rs.getString("cat"));
				j.setStatus(rs.getString("status"));
				j.setLocation(rs.getString("location"));
				j.setpDate(rs.getTimestamp("postdate") + "");

				jobList.add(j);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobList;

	}

	// get job based on id
	public Job getJobsDataById(int id) {
		Job j = null;

		try {
			String query = "select * from job where jobid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				j = new Job();

				j.setJobid(rs.getInt("jobid"));
				j.setTitle(rs.getString("title"));
				j.setJobdesc(rs.getString("jobdesc"));
				j.setCategory(rs.getString("cat"));
				j.setStatus(rs.getString("status"));
				j.setLocation(rs.getString("location"));
				j.setpDate(rs.getTimestamp("postdate") + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return j;

	}

	// update job based on id
	public boolean updateJob(Job job) {

		boolean isUpdated = false;
		try {
			String query = "update job set title=?,jobdesc=?,cat=?,status=?,location=? where jobid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getJobdesc());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());
			ps.setInt(6, job.getJobid());

			int i = ps.executeUpdate();

			if (i == 1) {
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;

	}

	// delete job by id

	public boolean deleteJobById(int id) {

		boolean isDeleted = false;

		try {
			String query = "delete from job where jobid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDeleted;

	}

}

