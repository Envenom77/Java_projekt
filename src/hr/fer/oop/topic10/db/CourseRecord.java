package hr.fer.oop.topic10.db;

public class CourseRecord {

		String courseID;
		String courseName;
		
		public CourseRecord(String courseId,String courseName){
			this.courseID = courseId;
			this.courseName = courseName;
		}
		
		public String getCourseID(){
			return this.courseID;
			
		}
		
		public String getCourseName(){
			return this.courseName;
		}
}
