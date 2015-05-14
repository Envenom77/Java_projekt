package hr.fer.oop.topic10.db;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class StudentDatabase {
	
	Map<String,StudentRecord> linkedHashMap = new LinkedHashMap<String,StudentRecord>();
	List<String> podaci = new ArrayList<String>();
		
	public StudentDatabase(List<String> podaci){
		this.podaci = podaci;
	}
	
	//napuni bazu
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
	
	// razdvaja podatke iz datoteke po tabulatoru
	public String[] razdvojiPodatke(String podaci){
		
		return podaci.split("\\t");
	}
	
	//vraca studenta sa određenim jmbagom
	public StudentRecord forJMBAG(String jmbag){
		
		if(linkedHashMap.containsKey(jmbag))
			return linkedHashMap.get(jmbag);
		
		else
			return null;
	}
	
	//filtrira studente po prezimenu
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
