package hr.fer.oop.topic10.db;


import java.util.List;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class StudentsFrame  extends JFrame {
	
	static List<String> studenti = new ArrayList<>();
	static List<String> predmeti = new ArrayList<>();
	static List<StudentRecord> student = new ArrayList<StudentRecord>();

	//konstruktor za studentframe
	public StudentsFrame(final StudentDatabase students,final CourseDatabase courses,final EnrolmentDatabase enroles){
		setVisible(true);
		setSize(450,450);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		
		final Container c = getContentPane();
        
		//napravi novi pane
        JTabbedPane pane = new JTabbedPane();
        
        //napravi panele i dodaj ih u pane
        final StudentsPanel panel = new StudentsPanel(students,courses,enroles,studenti,pane);
        final CoursesPanel panel2 = new CoursesPanel(students, courses, enroles, predmeti,pane,panel);
        
        pane.addTab("Students", panel);
        pane.addTab("Courses", panel2);
		
		c.add(pane,BorderLayout.CENTER);
		
		
	}
	public static void main(String [] args) throws IOException{
		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
		Database glavnaBaza = new Database("studenti.txt","predmeti.txt","upisanPredmeti.txt");
		
		final StudentDatabase students = glavnaBaza.getStudentTable();
		final CourseDatabase courses = glavnaBaza.getCourseTable();
		final EnrolmentDatabase enroles = glavnaBaza.getEnrolmentTable();
	
		
		// dodaj imena studenata u listu
		for(String jmbag: students.linkedHashMap.keySet()){
		
			studenti.add(students.forJMBAG(jmbag).GetFirstName() + " " + students.forJMBAG(jmbag).GetLastName());
			
		}
		
		//dodaj imena predmeta u listu
		for(String courseID: courses.linkedHashMap.keySet()){
			
			predmeti.add(courses.findById(courseID).getCourseName());
			
		}
		
		try {
			SwingUtilities.invokeAndWait(new Runnable(){
				
				public void run() {
					StudentsFrame frame = new StudentsFrame(students,courses,enroles);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
	
