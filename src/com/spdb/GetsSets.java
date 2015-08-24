package com.spdb;

public class GetsSets {
	
	//For user Table
	private String fName;
	private String lName;
	private String email = "";
	private String ruid;
	private String password;
	private String profBool;
	private String id;
	
	//For Course Table
	private String cname;
	private String cid;
	private String section;
	private String netid; //of the instructor
	private String prereq;
	
	//For New Request
	private String major;
	private String minor;
	private float gpa;
	private int numCredits;
	private String date;
	private String spn;
	private int rank;
	private int numCreds;
	private int cscreds;
	private String grade;
	private String comment;
	private String roomSize;
	private String message;
	private String adminBool;
	
	public String getAdminBool() {
		return adminBool;
	}
	public void setAdminBool(String adminBool) {
		this.adminBool = adminBool;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getNumCreds() {
		return numCreds;
	}
	public void setNumCreds(int numCreds) {
		this.numCreds = numCreds;
	}
	public int getCscreds() {
		return cscreds;
	}
	public void setCscreds(int cscreds) {
		this.cscreds = cscreds;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public int getNumCredits() {
		return numCredits;
	}
	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}
	
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getNetid() {
		return netid;
	}
	public void setNetid(String netid) {
		this.netid = netid;
	}
	public String getPrereq() {
		return prereq;
	}
	public void setPrereq(String prereq1) {
		this.prereq = prereq1;
	}
	
	public String getProfBool() {
		return profBool;
	}
	
	public String getRuid() {
		return ruid;
	}
	public void setRuid(String ruid) {
		this.ruid = ruid;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String isProfBool() {
		return profBool;
	}
	public void setProfBool(String profBool) {
		this.profBool = profBool;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
