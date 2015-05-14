package hr.fer.oop.topic10.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EnrolmentDatabase {

	List<EnrolmentRecord> lista = new ArrayList<EnrolmentRecord>();
	List<String> podaci = new ArrayList<String>();
	
	public EnrolmentDatabase(List<String> podaci) {
		super();
		this.podaci = podaci;
	}
	
	//napuni bazu podacima iz datoteke
	public void napuni(){
		
		for(int i = 0; i < podaci.size(); i++){
			
			String[] razdvojeniPodaci = razdvojiPodatke(podaci.get(i));
			EnrolmentRecord course = new EnrolmentRecord(razdvojeniPodaci[0],razdvojeniPodaci[1],Integer.parseInt(razdvojeniPodaci[2]));
			
			lista.add(course);
			
		}
		
	}
	
	//razdvoji podatke iz datoteke po tabulatoru
	public String[] razdvojiPodatke(String podaci){
		
		return podaci.split("\\t");
	}
	
	//pronađi studenta po jmbagu
	public Collection<EnrolmentRecord> findByStudent(String studentJMBAG){
		
		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
		
		for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).getStudentJMBAG().equals(studentJMBAG))
				mapa.add(lista.get(i));
		}
		return mapa;
	}
	
	//pronađi EnrolmentRecorde po courseID
	public Collection<EnrolmentRecord> findByCourse(String courseID){
		
		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
		
		for(int i = 0;i < lista.size(); i++){
			if(lista.get(i).getCourseID().equals(courseID))
				mapa.add(lista.get(i));
		}
		
		return mapa;
	}
	
	//pronađi rekorde po studentu i predmetu
	public EnrolmentRecord findByStudentAndCourse(String studentJMBAG,String courseID){
		
		for(int i = 0; i < lista.size(); i++){
			
			if( lista.get(i).getStudentJMBAG().equals(studentJMBAG))
				if(lista.get(i).getCourseID().equals(courseID))
					return lista.get(i);
		}
		return null;
	}
	
	//dodaj novi rekord
	public EnrolmentRecord newCourse(String studentJMBAG,String courseID){
		
		EnrolmentRecord novi = new EnrolmentRecord(courseID, studentJMBAG, 0);
		
		lista.add(novi);
		return novi;
	}
	
	//obnovi postojeci zapis
	public void updateEnrolment(EnrolmentRecord record){
		for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).getCourseID().equals(record.getCourseID()))
				if(lista.get(i).getStudentJMBAG().equals(record.getStudentJMBAG()))
					lista.get(i).setGrade(record.grade);
		}
				
	}
	
	
	//obrisi zapis
	public void deleteRecord(String studentJMBAG, String courseID){
		for(int i = 0; i < lista.size(); i++){
			if (lista.get(i).getStudentJMBAG().equals(studentJMBAG)){
				if (lista.get(i).getCourseID().equals(courseID))
						lista.remove(i);
			}
			else {
				System.out.println("Cannot delete student with jmbag:" + studentJMBAG + " and courseID: " + courseID);
			}
		}
				
	}
	
}
