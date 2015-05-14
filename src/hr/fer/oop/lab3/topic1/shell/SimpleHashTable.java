package hr.fer.oop.lab3.topic1.shell;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Josip Gatjal
 *
 */
public class SimpleHashTable<Object1,Object2> implements Iterable<SimpleHashTable.tableEntry<String, Integer>> {
	
	public  tableEntry<Object1,Object2>[] table;
	public int size = 0;
	
	/**
	 * constructor1
	 */
	public SimpleHashTable()
	{
		
		//napravi tablicu
		table = new tableEntry[16];
	}
	
	/**
	 * constructor2
	 * @param opacity, size of table that will be fixed to a power of 2
	 */
	public SimpleHashTable(int opacity){
		try{
			
			//provjera potencije od 2
			if ((opacity & (opacity - 1)) != 0){
				
				//ako nije potencija od 2, povecaj na najblizu vecu potenciju od 2
				while((opacity & (opacity - 1)) != 0){
					if (opacity % 2 != 0) opacity++;
					else opacity += 2;
				}
					
			}
			
			//napravi tablicu
			table = new tableEntry[opacity];
		}
		catch (NumberFormatException e ){
			System.out.println("Veličina mora biti int!");
			System.exit(1);
		}
		
		
	}
	
	/**
	 * 
	 * @param key, key of the given element
	 * @param value, value of the given element
	 */
	public void put(Object key, Object value) throws NullPointerException{
		
		
		
		tableEntry novi = new tableEntry(key,value,null);
		
		//pretvori u hash
		int brojSlota = key.hashCode() % table.length;
		if (brojSlota < 0) brojSlota *= -1;
		
		//ako je prvi član liste
		if (table[brojSlota] == null){
			
			table[brojSlota] = novi;
			this.size++;
			
			System.out.println("dodan element s kljucem " + key + " i vrijednosti " + value + " u slot " + brojSlota);
		}
		
		//ako nije prvi član
		else{
			
			//ako vec postoji kljuc
			boolean nadjeno = false;
			
			//pokusaj pronaci kljuc i promijeniti vrijednost
			nadjeno = promijeniVrijednost(table[brojSlota], key, value);
			
			
			// ako ne postoji takav kljuc, dodajemo novi element u listu
			if (!nadjeno){
				
				//postavlja novi element u slot
				traziZadnjiElement(table[brojSlota]).next = novi;
				System.out.println("dodan element s kljucem " + key + " i vrijednosti " + value + " u slot " + brojSlota);
				
				//povecavamo sveukupan broj elemenata u tablici
				this.size++;
				
			}
			
		}
		
	}
	
	//provjera da li postoji kljuc u tablici te ako postoji; mijenja ga
	/**
	 * 
	 * @param element, the first element of the slot
	 * @param key, the key of the element we want to add
	 * @param value, the value of the element we want to add
	 * @return , returns true if the element is found and the value changed, else returns false
	 */
	public boolean promijeniVrijednost(tableEntry element, Object key, Object value){
		
		// idemo dok ne nadjemo kljuc ili dok ne dodjemo do zadnjeg elementa
		while(element.key != key && element.next != null){
			
			element = element.next;
		}
		
		//ako je pronasao kljuc; postavi novu vrijendost i vrati true
		if (element.key == key){
			
			System.out.println("Promijena vrijednost elementa s kljucem " + key + " i vrijednosti " + element.value + 
					" u vrijednost " + value);
			
			element.value = value;
			return true;
		}
		
		//ako nije nasao vrati false
		return false;
		
	}
	
	/**
	 * 
	 * @param element, the first element of the slot
	 * @return , returns the last element of the slot
	 */
	public tableEntry traziZadnjiElement(tableEntry element){
		
		//idemo do zadnjeg elementa slota
		while(element.next != null) element = element.next;
		
		
		// vraca zadnji element slota
		return element;
		
	}
	
	//da li u tablici postoji kljuc
	/**
	 * 
	 * @param key, the key of the element we want to find
	 * @return , returns true if it's found, else returns false
	 */
	public boolean containsKey(Object key){
		//prodji kroz sve slotove
		for(int i = 0; i < table.length; i++){
			
			//postavi pocetni element na prvi element prvog slota
			tableEntry element = table[i];
			
			while(element != null){
				
				//ako je kljuc trazenog elementa pronadjen vrati true
				if (element.key == key) return true;
				
				//inace idi na sljedeci
				element = element.next;
			}
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @param value, the value of the element we want to find
	 * @return , returns true if found, else false
	 */
	public boolean containsValue(Object value){
		//prodji kroz sve slotove
		for(int i = 0; i < table.length; i++){
			//postavi pocetni element na prvi element prvog slota
			tableEntry element = table[i];
			
			while(element != null){
				
				//ako je vrijednost trazenog elementa pronadjena vrati true
				if(element.value == value) return true;
				
				//inace idi na sljeqdeci
				element = element.next;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param key , the key of the element we want to remove
	 */
	public void remove(Object key){
		
		boolean obrisan = false;
		
		//prodji kroz sve slotove
		for(int i = 0; i < table.length; i++){
			
			if (table[i] != null){
				//postavi pocetni element na prvi element prvog slota
				tableEntry element = table[i];
						
				//ako je to jedini element
				if (element.next == null && element.key == key){
			
					table[i] = null;
					System.out.println("Izbrisan element s kljucem: " + key);
					obrisan = true;
							
					}
						
				//ako je to prvi element
				if (element.key == key && element.next != null){
					
					//izbrisi
					table[i] = element.next;
					System.out.println("Izbrisan element s kljucem: " + key);
					obrisan = true;
							
				}
						
				//ako ima vise od jednog elementa u slotu
				while(obrisan == false &&element.next != null){
							
					//ako je sljedeci element trazeni element
					if (element.next.key == key){
								
						//izbrisi
						element.next = element.next.next;
						System.out.println("Izbrisan element s kljucem: " + key);
						obrisan = true;
								
					}
							
					element = element.next;
				}
			}
		}
		
		//ako trazeni element ne postoji
		if(!obrisan){
			
			System.out.println("Brisanje elementa s kljucem " + key + " nije  moguce! Element s tim kljucem ne postoji.");
			}
	}
	
	/**
	 * 
	 * @param key, the key of the element who's value we want to find
	 * @return , returns the required value or -1 if the value's not found
	 */
	public Object get(Object key) {
		
		for (int i = 0; i < table.length ; i++){
			
			//pomocni element za trazenje trazenog kljuca
			tableEntry element = table[i];
			
			while(element != null){
				
				//ako smo nasli trazeni kljuc
				
				if (element.key.equals(key)){
					return element.value;
				}
				
				//inace, idi dalje
				element = element.next;
			}
		}
		
		return -1;
		
	}
	
	/**
	 * 
	 * @return , returns true if the slot is empty, else false
	 */
	public boolean isEmpty(){
		
		for(int i = 0; i < table.length; i++){
			
		
			if (table[i] != null) return false;
		
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param args, arguments from the user
	 */
	public static void main(String[] args) {
		
		SimpleHashTable<String,Integer> exams = new SimpleHashTable<>();
		
		//stvaranje elemenata
		exams.put("Ivana", Integer.valueOf(5));
		exams.put("Janko", Integer.valueOf(4));

			
		for(String name : exams.keys()) {
			System.out.println("Ime = " + name);
		}
		
		for(Integer grade : exams.values()) {
			System.out.println("Ocjena = " + grade);
		}
		
		for(SimpleHashTable.tableEntry<String,Integer> pair : exams) {
			System.out.println("(Ime, Ocjena) = (" + pair.getKey() + ", " + pair.getValue()+")");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator iterator() {
		return new CollectionIterator();
	}
	
	private class CollectionIterator implements Iterator<Object>{
		
		// brojSlota
		int index = 0;
		
		//broj elemenata unutar trenutnog slota
		int broj = 0;
		
		//ukupno elemenata
		int ukupno = 0;
		
		@Override
		public boolean hasNext() {
			return ukupno < SimpleHashTable.this.size;
		}

		@Override
		public tableEntry next() {
			if (! hasNext()) throw new NoSuchElementException();
			while(table[index] == null && index < table.length) index++;
			
			//ako je to prvi element
			if (ukupno == 0){
				
				ukupno++;
				//ako ima samo jedan element vrati sljedeci slot
				if(table[index].next == null) return table[index++];
				
				//ako ima vise
				broj ++;
				return table[index];
			}
			

			
			tableEntry element = table[index];
			//ako ima vise elemenata unutar slota, dodji to trazenog tj sljedeceg elementa i vrati ga
			for(int i = 0; i < broj; i++)
				element = element.next;
			// ako je to zadnji element u slotu
			if(element.next == null){
				index++;
				broj = 0;
				ukupno++;
				return element;
			}
			//ako nije zadnji
			broj++;
			ukupno++;
			return element;
		}

		@Override
		public void remove() {
			
		}
		
	}
	
	
	public Iterable<String> keys() {
		return new KeyIterable();
		
	}	
	
	public class KeyIterable implements Iterable{

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return new KeyIterator();
		}
		
		
		private class KeyIterator implements Iterator{

			// brojSlota
			int index = 0;
			
			//broj elemenata unutar trenutnog slota
			int broj = 0;
			
			//ukupno elemenata
			int ukupno = 0;
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return ukupno < SimpleHashTable.this.size;
			}

			@Override
			public Object next() {
				if (! hasNext()) throw new NoSuchElementException();
				while(table[index] == null && index < table.length) index++;
				
				//ako je to prvi element
				if (ukupno == 0){
					
					ukupno++;
					//ako ima samo jedan element vrati sljedeci slot
					if(table[index].next == null) return table[index++].getKey();
					
					//ako ima vise
					broj ++;
					return table[index].getKey();
			}
				
				tableEntry element = table[index];
				//ako ima vise elemenata unutar slota, dodji to trazenog tj sljedeceg elementa i vrati ga
				for(int i = 0; i < broj; i++)
					element = element.next;
				// ako je to zadnji element u slotu
				if(element.next == null){
					index++;
					broj = 0;
					ukupno++;
					return element.getKey();
				}
				//ako nije zadnji
				broj++;
				ukupno++;
				return element.getKey();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}
	
	
	
	public Iterable<Integer> values() {
		return new ValueIterable();
		
	}	
	
	public class ValueIterable implements Iterable{

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return new ValueIterator();
		}
		
		
		private class ValueIterator implements Iterator{

			// brojSlota
			int index = 0;
			
			//broj elemenata unutar trenutnog slota
			int broj = 0;
			
			//ukupno elemenata
			int ukupno = 0;
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return ukupno < SimpleHashTable.this.size;
			}

			@Override
			public Object next() {
				if (! hasNext()) throw new NoSuchElementException();
				while(table[index] == null && index < table.length) index++;
				
				//ako je to prvi element
				if (ukupno == 0){
					
					ukupno++;
					//ako ima samo jedan element vrati sljedeci slot
					if(table[index].next == null) return table[index++].getValue();
					
					//ako ima vise
					broj ++;
					return table[index].getValue();
			}
				
				tableEntry element = table[index];
				//ako ima vise elemenata unutar slota, dodji to trazenog tj sljedeceg elementa i vrati ga
				for(int i = 0; i < broj; i++)
					element = element.next;
				// ako je to zadnji element u slotu
				if(element.next == null){
					index++;
					broj = 0;
					ukupno++;
					return element.getValue();
				}
				//ako nije zadnji
				broj++;
				ukupno++;
				return element.getValue();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}
	
	public static class tableEntry<String,Integer> {
		Object key = 0;
		Object value = 0;
		tableEntry next;
		
		/**
		 * 
		 * @param key, key of the element that is being created
		 * @param value, value of the element that is being created
		 * @param next, the upcoming element in the list
		 */
		public tableEntry(Object key, Object value, tableEntry next){
			this.key = key;
			this.value = value;
			this.next = next;
			
		}
		
		/**
		 * 
		 * @return , returns the key of the element
		 */
		public Object getKey(){
			
			return this.key;
			
		}
		
		/**
		 * 
		 * @return, returns the value of the element
		 */
		public Object getValue(){
			
			return this.value;
		}
		
		/**
		 * 
		 * @param value , the value we want to set the element to
		 */
		public void setValue(int value){
			this.value = value;
		}

	}

	
	
}
