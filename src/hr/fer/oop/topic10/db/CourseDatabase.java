package hr.fer.oop.topic10.db;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseDatabase {
	
	
	Map<String,CourseRecord> linkedHashMap = new LinkedHashMap<String,CourseRecord>();
	List<String> podaci = new ArrayList<String>();
	
	public CourseDatabase(List<String> podaci) {
		super();
		this.podaci = podaci;
	}
	
	//napuni bazu podacima iz datoteke
	public void napuni(){
			
			for(int i = 0; i < podaci.size(); i++){
				
				String[] razdvojeniPodaci = razdvojiPodatke(podaci.get(i));
				
				CourseRecord student = new CourseRecord(razdvojeniPodaci[0],razdvojeniPodaci[1]);
				
				linkedHashMap.put(razdvojeniPodaci[0], student);
				
			}
			
		}
	
	//razvdvoji podatke iz datoteke po tabulatoru
	public String[] razdvojiPodatke(String podaci){
			
			return podaci.split("\\t");
		}
	
	//pronađi određeni predmet po courseID
	public CourseRecord findById(String courseID){
		if(linkedHashMap.containsKey(courseID))
			return linkedHashMap.get(courseID);
		else
			return null;
	}
		
	// case insensitive searching with * e.g. "objektno*"
	public CourseRecord findFirstByName(String partialCourseName){
		for(String course: linkedHashMap.keySet()){
			
			if(accepts(linkedHashMap.get(course),partialCourseName))
				return linkedHashMap.get(course);
		}
		return null;
		
	}
	
	//metoda za provjeru prezimena na temelju polozaja zvjezdice
	public boolean accepts(CourseRecord record,String partialCourseName){
		
		//zvjezdica na pocetku
		if(polozajZvijezdice(partialCourseName) == 1) {
			if (record.getCourseName().endsWith(partialCourseName.split("\\*")[1]))
				return true;
		}
		
		//zvjezdica na kraju
		else if(polozajZvijezdice(partialCourseName) == partialCourseName.length()){
			if(record.getCourseName().startsWith(partialCourseName.split("\\*")[0]))
				return true;
		}
		//zvjezdica nema
		else if(polozajZvijezdice(partialCourseName) == 0){
			if(record.getCourseName().equals(partialCourseName))
				return true;
		}
		
		//zvjezdica je unutar slova
		else{
			if(record.getCourseName().startsWith(partialCourseName.split("\\*")[0]))
				if(record.getCourseName().endsWith(partialCourseName.split("\\*")[1]))
					return true;
		}
		
		//nije pronasao prezime
		return false;
		
	}
	
	//vraca index polozaja zvjezdice u masci prezimena
	public int polozajZvijezdice(String prezime){
		return prezime.indexOf('*') + 1;
	}
}