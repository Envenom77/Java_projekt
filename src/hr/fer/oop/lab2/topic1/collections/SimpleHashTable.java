package hr.fer.oop.lab2.topic1.collections;




/**
 * 
 * @author Josip Gatjal
 *
 */
public class SimpleHashTable {
	
	tableEntry[] table;
	public int size = 0;
	
	/**
	 * constructor1
	 */
	public SimpleHashTable()
	{
		
		//napravi tablicu
		this.table = new tableEntry[16];
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
			this.table = new tableEntry[opacity];
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
	
//	//ispisuje sve elemente
//	public void ispis(){
//		int i = 0;
//		while(i < table.length){
//			if (table[i] != null) System.out.println("slot broj: " + i);
//			ispisiSlot(table[i]);
//			i++;
//			
//		}
//		
//	}
//	
//	//ispis svakog slota
//	public void ispisiSlot(tableEntry element){
//		
//		while(element != null){
//			System.out.println("kljuc je: " + element.key + ", a vrijendost je:  " + element.value);
//			element = element.next;
//		}
//	}
	
	/**
	 * 
	 * @param key of the element
	 * @param value of the element
	 * @return formated string
	 */
	public String toString(int key, int value){
		
		return super.toString() + "(" + key + "," + value+ ")";
		
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
				if (element.key == key){
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
		SimpleHashTable tablica = new SimpleHashTable(2);
		
		try {
			//stvaranje elemenata
			tablica.put(3, 4);
			tablica.put(5, 3);
			tablica.put(3,3);
			tablica.put(2,2);
		}
		catch (NullPointerException e){
			System.out.println("Null pointer!");
			System.exit(1);
			
		}
		
		
		//dohvacanje vrijednosti elementa
		Object brojPapira = 0;
		
		brojPapira = tablica.get(3);
		System.out.println(brojPapira);
		
		
		//provjera kljuca elementa
		if(tablica.containsKey(null)) System.out.println("Tablica sadrzi element s kljucem 5");
		else System.out.println("Tablica ne sadrzi element s kljucem 5");
		

		
		
		//provjera vrijednosti elementa
		try {
			if(tablica.containsValue(2)) System.out.println("Tablica sadrzi element s vrijednosti 2");
			else System.out.println("Tablica ne sadrzi element s vrijednosti 2");
		}
		catch (NullPointerException e){
			System.out.println("Null pointer");
		}
		
//		//ispis svih slotova
//		tablica.ispis();
		
		System.out.println("Velicina tablice je: " + tablica.size);
		
		//brisanje elemenata
		tablica.remove(null);
		tablica.remove(2);
		tablica.remove(3);
		tablica.remove(5);
		
	}
	
	
}
