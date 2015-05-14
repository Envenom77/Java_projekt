package hr.fer.oop.week9.db;

public class StudentRecord {
	
	String jmbag;
	String lastName;
	String firstName;
	int finalGrade;
	
	public StudentRecord(String jmbag,String lastName, String firstName, int finalGrade){
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
		
	}
	
	public String GetJmbag(){
		return this.jmbag;
	}
	
	public String GetLastName(){
		return this.lastName;
	}
	
	
	public String GetFirstName(){
		return this.firstName;
	}
	
	public int GetFinalGrade(){
		return this.finalGrade;
	}
	
	
}
