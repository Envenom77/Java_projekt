package hr.fer.oop.lab1.topic1.prob5;

/**
 * 
 * @author josipGatjal
 *
 */
public class PrimeNumbers {
	
	/**
	 * 
	 * @param args (arguments)
	 */
	public static void main(String[] args){
		
		if (args.length != 1){
			System.out.println("Erorr.Please give 1 argument that is greater or equal to zero");
			System.exit(0);
		}
		
		//učitavanje argumenta
		int n = Integer.parseInt(args[0]);
		
		//brojac prostih brojeva
		int counter = 0;
		int broj = 1;
		System.out.println("You requested " + n + " first prime numbers:");
		while(counter != n){
			//kroz sve brojeve vece od 1 (1 i 0 nisu prosti)
			broj++;
			
			//ako je prost, ispisi ga i podigni brojac za 1
			if(jeProst(broj)){
				
				System.out.println(broj);
				counter++;
			}
			
		}	
		
		
	}
	
	//provjera prostoće
	/**
	 * 
	 * @param broj (number that needs to be checked prime/not prime)
	 * @return boolean
	 */
	static boolean jeProst(int broj){
		
		
		if(broj % 2 == 0 && broj!= 2) return false;
		
		for(int i = 3; i * i <= broj; i+=2){
			
			if (broj % i == 0){
			
				return false;
			}
		}
		
		return true;
	}
	
	
}