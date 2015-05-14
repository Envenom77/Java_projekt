package hr.fer.oop.topic10.db;

public class EnrolmentRecord {
	
	String courseID;
	String studentJMBAG;
	int grade;
	
	public EnrolmentRecord(String courseID, String studentJMBAG, int grade) {
		super();
		this.courseID = courseID;
		this.studentJMBAG = studentJMBAG;
		this.grade = grade;
	}
	
	public String getCourseID(){
		return this.courseID;
	}
	
	public String getStudentJMBAG(){
		return this.studentJMBAG;
	}
	
	public int getGrade(){
		return this.grade;
	}
	
	public void setGrade(int grade){
		this.grade = grade;
	}

}
