package hr.fer.oop.topic10.db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	String dat1,dat2,dat3;
	static List<String> lista = new ArrayList<String>();
	
	public Database(String dat1, String dat2, String dat3) {
		super();
		this.dat1 = dat1;
		this.dat2 = dat2;
		this.dat3 = dat3;
	}
	
	public StudentDatabase getStudentTable() throws IOException{
		File file = new File(dat1);
		StudentDatabase studentBase = new StudentDatabase(procitajFile(file,1));
		studentBase.napuni();
		return studentBase;
	}
	
	public CourseDatabase getCourseTable() throws IOException{
		File file = new File(dat2);
		CourseDatabase courseBase = new CourseDatabase(procitajFile(file,2));
		courseBase.napuni();
		return courseBase;
	}
	
	public EnrolmentDatabase getEnrolmentTable() throws IOException{
		File file = new File(dat3);
		EnrolmentDatabase enrolmentBase = new EnrolmentDatabase(procitajFile(file,3));
		enrolmentBase.napuni();
		return enrolmentBase;
	}
	
	public static List<String> procitajFile(File file,int num) throws IOException{
			if (num == 1)
				lista = Files.readAllLines(Paths.get("studenti.txt"),StandardCharsets.UTF_8);
			
			if(num == 2)
				lista = Files.readAllLines(Paths.get("predmeti.txt"),StandardCharsets.UTF_8);
			
			if(num == 3)
				lista = Files.readAllLines(Paths.get("upisaniPredmeti.txt"),StandardCharsets.UTF_8);
			
			return lista;
	}

}
