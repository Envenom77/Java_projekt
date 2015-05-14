package hr.fer.oop.lab1.topic1.prob1;

import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * @author josipGatjal
 *
 */
public class Rectangle {
	
	/**
	 * 
	 * @param args
	 */
	public static void main (String[] args){
		int visina = 0,sirina = 0;
		
		// ako je zadan krivi broj argumenata
		if (args.length != 2 && args.length != 0)
		{
			System.out.println("The number of arguments must be 2!");
			System.exit(0);
		}
		
		//ako je zadan dobar broj argumenata
		else if (args.length == 2)
		{
			visina = Integer.parseInt(args[0]);
			sirina = Integer.parseInt(args[1]);
		}
		
		// inace citaj sa standardnog ulaza
		else 
		{
			//unos visine
			System.out.println("give height");
			visina = unosVisinaSirina();
			
			//unos sirine
			System.out.println("give width");
			sirina = unosVisinaSirina();
			
		}
		
		//racunanje opsega
		int opseg = 2 * (sirina + visina);
		
		//racunanje povrsine
		int povrsina = sirina * visina;
		
		//ispis
		System.out.println("Height: " + visina + "  Width: " + sirina + "\nArea is: " + opseg + "\nPerimeter is: " + povrsina);
	
	}
	
	// metoda za unos visine i sirine
	/**
	 * 
	 * @return parametar type int
	 */
	public static int unosVisinaSirina(){

		Scanner input = new Scanner(System.in);
		
		String unos1 = input.nextLine().trim();
		
		//ucitava dok ne dobije pozitivan broj
		while(unos1.equals("") || jePozitivan(unos1) == false){
			
			if(unos1.equals("")) {
				System.out.println("Empty string! Enter the paramater again.");
			}
			
			else {
				System.out.println("Negative number! Enter the paramater again.");
			}
			
			unos1 = input.nextLine();
		}
		
		//ako je sve dobro
		int parametar = 0;
		try{
		parametar = Integer.parseInt(unos1.trim());
		}
		catch (NumberFormatException e) {
			System.out.println("ne valja");
			e.printStackTrace();
			System.exit(1);
		}
		 
		finally {// vrati parametar(visina/sirina)
		return parametar;
		}
	}
	
	//vraca true ako je broj pozitivan
	/**
	 * 
	 * @param unos1 (input of the user)
	 * @return boolean (if the input is positive return true, else false)
	 */
	public static boolean jePozitivan(String unos1){
		int provjera =  0;
		try { 
		provjera = Integer.parseInt(unos1.trim());
		}
		catch ( NumberFormatException e) {
			System.out.println("nista");
		}
		
		if (provjera < 0){
			return false;
		}
		
		else return true;
	}

}
	
	



