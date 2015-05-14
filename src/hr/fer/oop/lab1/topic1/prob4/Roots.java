package hr.fer.oop.lab1.topic1.prob4;

/**
 * 
 * @author josipGatjal
 *
 */
public class Roots {
	
	/**
	 * 
	 * @param args (arguments)
	 */
	public static void main(String[] args){
		
		//krivi broj argumenata
		if (args.length != 3){
			System.out.println("3 arguments are required!");
			System.exit(0);
		}
		//uctivanje argumenata
		double realni = Double.parseDouble(args[0]);
		double imaginarni = Double.parseDouble(args[1]);
		int korijen = Integer.parseInt(args[2]);
		
		//pozivanje funkcije za izracun
		izracunajKorijen(realni,imaginarni,korijen);
		
	}
	
	/**
	 * 
	 * @param realni (real part of the complex number)
	 * @param kompleksni (imaginary part of the complex number)
	 * @param korijen (which root to calculate)
	 */
	static void izracunajKorijen(double realni,double kompleksni,int korijen){
		
		//izracun n-ti korijen iz r
		double r = Math.sqrt(realni*realni+kompleksni*kompleksni);
		r = Math.pow(r, (double)1/korijen);
		
		//izracun kuta
		double kut = Math.atan(kompleksni/realni);
	
		
		double korijenRealni = 0;
		double korijenImaginarni = 0;
		
		System.out.println("You requested the calculation of " + korijen + " roots for " + realni +" and " + kompleksni);
		
		//izracunjavanje svih korijena zadanog kompleksnog broja
		for(int i = 0; i < korijen; i++){
			
			korijenRealni = izracunajRealni(kut,i,korijen);
			korijenImaginarni = izracunajImaginarni(kut,i,korijen);
			
			//ovisno o imaginarnom dijelu korijena ispisi + ili -
			if (korijenImaginarni < 0)
				
				System.out.println(i + ") " + Math.round(r*korijenRealni) + " - " + Math.round(r*(Math.abs(korijenImaginarni))) + "i\n");
				
			else
				System.out.println(i + ") " + Math.round(r*korijenRealni) + " + " + Math.round(r*(Math.abs(korijenImaginarni))) + "i\n");
			
			
		}
		
	}
	
	// vraca realni dio korijena tipa double
	/**
	 * 
	 * @param kut
	 * @param i
	 * @param korijen
	 * @return double (real part of the root)
	 */
	static double izracunajRealni(double kut,int i,int korijen){
		
		return Math.cos((kut + 2 * i * Math.PI) / korijen);
		
	}
	
	// vraca imaginarni dio korijena tipa double
	/**
	 * 
	 * @param kut
	 * @param i
	 * @param korijen
	 * @return double (imaginary part of the root)
	 */
	static double izracunajImaginarni(double kut,int i,int korijen){
		
		return Math.sin((kut + 2 * i * Math.PI) / korijen);
		
	}
	
}
