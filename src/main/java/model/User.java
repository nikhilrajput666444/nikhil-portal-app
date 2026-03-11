package model;

public class User {

	private int userid;
	private String userfirstname;
	private String userlastname;
	private String userEmail;
	private String role;//for admin and normal
	private String password;
	private String userimage;
	private long mobilenumber;
	
	//constructor
	
	public User() {
		
	}

	public User(String userfirstname, String userlastname, String userEmail, String role, String password,
			String userimage, long mobilenumber) {
		super();
		this.userfirstname = userfirstname;
		this.userlastname = userlastname;
		this.userEmail = userEmail;
		this.role = role;
		this.password = password;
		this.userimage = userimage;
		this.mobilenumber = mobilenumber;
	}
	
	//getter and setter 
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserimage() {
		return userimage;
	}

	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	
	
	
	
	
}
