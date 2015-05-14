package hr.fer.oop.week9.db;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class StudentDatabase {
	
	Map<String,StudentRecord> linkedHashMap = new LinkedHashMap<String,StudentRecord>();
	List<String> podaci = new ArrayList<String>();
		
	public StudentDatabase(List<String> podaci){
		this.podaci = podaci;
	}
	
	// napuni bazu podacima
	public void napuni(){
		
	
		
		for(int i = 0; i < podaci.size(); i++){
			
			String[] razdvojeniPodaci = razdvojiPodatke(podaci.get(i));
			
			StudentRecord student = new StudentRecord(razdvojeniPodaci[0],razdvojeniPodaci[1],
					razdvojeniPodaci[2],Integer.parseInt(razdvojeniPodaci[3]));
			
			linkedHashMap.put(razdvojeniPodaci[0], student);
			
		}
		
	}
	
	public void ispis(){
		
		System.out.println(linkedHashMap.keySet());
	}
	
	public String[] razdvojiPodatke(String podaci){
		
		return podaci.split("\\t");
	}
	
	//trazi studenta po jmbagu
	public StudentRecord forJMBAG(String jmbag){
		
		if(linkedHashMap.containsKey(jmbag))
			return linkedHashMap.get(jmbag);
		
		else
			return null;
	}
	
	//filtrira studente po prezimenima
	public List<StudentRecord> filter(IFilter filter){
		
		List<StudentRecord> lista = new ArrayList<StudentRecord>();
		
		for(String jmbag : linkedHashMap.keySet()){
			
			if(filter.accepts(linkedHashMap.get(jmbag))){
				lista.add(linkedHashMap.get(jmbag));
			}
		}
		
		return lista;
	}

}
