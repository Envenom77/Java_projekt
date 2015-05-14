package hr.fer.oop.week9.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
	
	static List<String> lista = new ArrayList<String>();
	static List<StudentRecord> studenti = new ArrayList<StudentRecord>();
	
	public static void main(String [] args) throws IOException{
		
		File file = new File("database.txt");
		
		StudentDatabase baza = new StudentDatabase(procitajFile(file));
		baza.napuni();
		
		BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
		
		String input;
		System.out.print("> ");
		
		//emulator
		while(!(input = br.readLine()).equals("quit")){
			if(provjeriUnos(input)){
				obradiUnos(input,baza);
				
			}
			else{
				ispisiError();
				continue;
			}
		
			
		}
		System.out.println("Thank you for using this database!");
	}
	
	
	
	public static List<String> procitajFile(File file) throws IOException{
		
		lista = Files.readAllLines(Paths.get("database.txt"),StandardCharsets.ISO_8859_1);
		
		return lista;
				
	}
	
	public static boolean provjeriUnos(String input){
		
		String[] unos = input.split(" ");
		
		if(unos[0].equals("query"))
				return true;
		return false;
				
	}
	
	public static void ispisiError(){
		System.out.println("Unknown command!");
		System.out.print("> ");
	}
	
	public static void obradiUnos(String input,StudentDatabase baza){
		
		String naredba = "";
		
		String[] unos = input.split(" ");
		for(int i = 1; i < unos.length; i++){
			naredba += unos[i];
		}
		String[] split = naredba.split("=");
		if(provjeriNaredbu(split[0].trim())){
			
			if(split[0].trim().equals("lastName")){
				String maska = obradiMasku(split[1].trim());
				ispisiPoPrezimenu(maska,baza);
			}
			
			if(split[0].trim().equals("jmbag")){
				String jmbag = obradiMasku(split[1].trim());
				ispisiPoJmbagu(jmbag,baza);
			}
			
		}
		else{
			ispisiError();
			return;
		}
			
	}
	
	public static boolean provjeriNaredbu(String naredba){
		if(!naredba.equals("lastName"))
				if(!naredba.equals("jmbag"))
					return false;
		return true;
	}
	
	public static String obradiMasku(String maska){
		
		maska = maska.replace("\"", "");
		
		return maska;
	}
	
	public static void ispisiPoPrezimenu(String maska,StudentDatabase baza){
		
		LastNameFilter lastName = new LastNameFilter(maska);
		studenti = baza.filter(lastName);
		int brojStudenata = 0;
		
		for(StudentRecord student: studenti){
			System.out.println(student.GetJmbag() + " " + student.GetLastName() + " " + student.GetFirstName() + " " + student.GetFinalGrade());
			brojStudenata++;
		}
		System.out.println("Records Selected: " + brojStudenata);
		System.out.print("> ");
	}
	
	public static void ispisiPoJmbagu(String jmbag,StudentDatabase baza){
		
		StudentRecord student = baza.forJMBAG(jmbag);
		System.out.println(student.GetJmbag() + " " + student.GetLastName() + " " + student.GetFirstName() + student.GetFinalGrade());
		System.out.print("> ");
	}
	

}
