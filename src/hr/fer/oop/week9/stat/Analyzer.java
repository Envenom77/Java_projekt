package hr.fer.oop.week9.stat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class Analyzer {
	 
	static List<String> lista = new ArrayList<String>();
	static List<String> ekstenzije = new ArrayList<String>();
	
	
	static class Values {
		int brojPonavljanja = 0;
		long veličina = 0;
		
		public Values(int brojPonavljanja, long veličina){
			this.brojPonavljanja = brojPonavljanja;
			this.veličina = veličina;
		}
	}
	
	//ekstenzije
	static HashMap<String,Values> mapa = new HashMap<String,Values>();
	
	//pocetna slova
	static HashMap<String,Values> mapa2 = new HashMap<String,Values>();
	
	//obradi file, stavi ekstenzije, broj ekstenzija i veličinu
	public static void listajFile(File dat){
		
		File[] files = new File(dat.getAbsolutePath()).listFiles();
		
		
		for(File file : files){
			if (file.isFile()){
				
				//EKSTENZIJE
				
				String ekstenzija = pronadjiEkstenziju(file.getName());
				
				//ako vec postoji ekstenzija
				if(mapa.containsKey(ekstenzija)){
					
					//dodaj ekstenziju sa brojem ponavljanja i velicinom
					Values values = mapa.get(ekstenzija);
					values.brojPonavljanja++;
					values.veličina += file.length();
					mapa.put(ekstenzija,values);
					
					
				}
				
				//ako ne postoji ekstenzija, napravi novi hash
				else {
					
					//dodaj novu ekstenziju sa defaultnim vrijednostima
					Values pocetneVelicine = new Values(1,file.length());
					mapa.put(ekstenzija, pocetneVelicine);
				}
				
				
				//POCETNA SLOVA
				
				
				String pocetnoSlovo = String.valueOf(pronadjiSlovo(file.getName()));
				
				//ako postoji slovo
				if(mapa2.containsKey(pocetnoSlovo)){
					Values values = mapa2.get(pocetnoSlovo);
					values.brojPonavljanja++;
					values.veličina += file.length();
					mapa2.put(pocetnoSlovo,values);
				}
				
				
				//ako je novo slovo
				else {
					Values pocetneVelicine = new Values(1,file.length());
					mapa2.put(pocetnoSlovo, pocetneVelicine);
				}
				
				
				
				lista.add(file.getName());
			}
			
			//ako je direktorij pozovi rekurz
			else {
				if(file.isDirectory()){
					listajFile(file);
				}
			}
		}
		
	}
	
	//vrati ekstenziju datoteke
	public static String pronadjiEkstenziju(String ime){
		String ekstenzija = ime.split("\\.")[1];
		return ekstenzija;
	}
	
	//pronadji pocetno slovo filea
	public static char pronadjiSlovo(String ime){
		String samoIme = ime.split("\\.")[0];
		samoIme = samoIme.toLowerCase();
		
		char slovo =  samoIme.charAt(0);
		return slovo;
	}
	

	//da li file ima ekstenziju
	public static boolean imaEkstenzija(String ekstenzija){
		if(ekstenzije.size() != 0)
			
			for(String postojecaEkstenzija : ekstenzije){
				if(ekstenzija.equals(postojecaEkstenzija))
					return true;
			}
		
		return false;
	}
	
	public static void main(String args[]){
		
		//zadaj direktorij
		File file = new File("/home/korisnik/proba2");
		if(file.isDirectory()){
			listajFile(file);
		}
		Collections. sort(lista);
		
		//pronadjiEkstenzije(lista);
		
		
		
		
		for(String ekstenzija2 : mapa.keySet()){
			Values values = mapa.get(ekstenzija2);
			System.out.println(ekstenzija2 + " " + values.brojPonavljanja +  " " + values.veličina);
			
		}
		
		
		//sortiraj leksikografski
		TreeMap sortiranaMapa2 = new TreeMap(mapa2);
		
		//ispisi
		for(Object pocetnoSlovo : sortiranaMapa2.keySet()){
			Values values = mapa2.get(pocetnoSlovo);
			System.out.println(pocetnoSlovo + " " + values.brojPonavljanja + " " + values.veličina);
		}
	}

}
