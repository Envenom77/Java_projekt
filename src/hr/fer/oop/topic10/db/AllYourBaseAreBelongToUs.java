package hr.fer.oop.topic10.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class AllYourBaseAreBelongToUs {
//	
//	public static void main(String [] args) throws IOException{
//		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
//		Database glavnaBaza = new Database("studenti.txt","predmeti.txt","upisanPredmeti.txt");
//		
//		StudentDatabase students = glavnaBaza.getStudentTable();
//		CourseDatabase courses = glavnaBaza.getCourseTable();
//		EnrolmentDatabase enroles = glavnaBaza.getEnrolmentTable();
//		
//		mapa =  (List<EnrolmentRecord>) enroles.findByStudent("0000000002");
//		for(int i = 0; i < mapa.size(); i++){
//			
//			String courseID = mapa.get(i).getCourseID();
//			System.out.println(courses.findById(courseID).getCourseName());
//			
//			
//		}
//	}
//
//}
