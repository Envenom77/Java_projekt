package hr.fer.oop.week9.db;

public class LastNameFilter implements IFilter {

	String prezime;	
	
	public LastNameFilter(String prezime) {
		this.prezime = prezime;
		
	}
	
	//metoda za provjeru polozaja zvijezdice
	@Override
	public boolean accepts(StudentRecord record) {
		if(prezime.equals("*")) return true;
		
		if(polozajZvijezdice(prezime) == 1) {
			if (record.GetLastName().endsWith(prezime.split("\\*")[1]))
				return true;
		}
		
		
		else if(polozajZvijezdice(prezime) == prezime.length()){
			if(record.GetLastName().startsWith(prezime.split("\\*")[0]))
				return true;
		}
		else if(polozajZvijezdice(prezime) == 0){
			if(record.GetLastName().equals(prezime))
				return true;
		}
		
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
