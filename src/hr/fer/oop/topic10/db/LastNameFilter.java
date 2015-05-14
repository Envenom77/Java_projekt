package hr.fer.oop.topic10.db;

public class LastNameFilter implements IFilter {

	String prezime;	
	
	public LastNameFilter(String prezime) {
		this.prezime = prezime;
		
	}
	
	//metoda za provjeru prezimena na temelju polozaja zvjezdice
	@Override
	public boolean accepts(StudentRecord record) {
		
		//zvjezdica na pocetku
		if(polozajZvijezdice(prezime) == 1) {
			if (record.GetLastName().endsWith(prezime.split("\\*")[1]))
				return true;
		}
		
		//zvjezdica na kraju
		else if(polozajZvijezdice(prezime) == prezime.length()){
			if(record.GetLastName().startsWith(prezime.split("\\*")[0]))
				return true;
		}
		//zvjezdice nema
		else if(polozajZvijezdice(prezime) == 0){
			if(record.GetLastName().equals(prezime))
				return true;
		}
		
		//zvjezdica je između slova
		else{
			if(record.GetLastName().startsWith(prezime.split("\\*")[0]))
				if(record.GetLastName().endsWith(prezime.split("\\*")[1]))
					return true;
		}
		
		
		return false;
	}
	
	public int polozajZvijezdice(String prezime){
		return prezime.indexOf('*') + 1;
	}
	

}
