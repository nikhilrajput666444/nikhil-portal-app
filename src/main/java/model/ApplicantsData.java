package model;

public class ApplicantsData {
	
	private int applicatonId;
	private int userId;
	private String fullName;//fname+lname
	private String email;
	
	private String mobno;
	
	private String appliedDate;
	
	public int getApplicatonId() {
		return applicatonId;
	}
	public void setApplicatonId(int applicatonId) {
		this.applicatonId = applicatonId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	
	
	
	
	
  
}

