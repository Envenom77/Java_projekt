package hr.fer.oop.lab1.topic1.prob6;

/**
 * 
 * @author josipGatjal
 *
 */
public class NumberDecomposition {
	
	/**
	 * 
	 * @param args (arguments)
	 */
	public static void main(String[] args){
		
		if (args.length != 1 || Integer.parseInt(args[0]) <= 1){
			System.out.println("Erorr.Please give one argument that is greater then 1!");
			System.exit(0);
		}
		
		//ucitavanje argumenta
		int broj = Integer.parseInt(args[0]);
		System.out.println("You requested decomposition of number " + broj + " :");
		rastaviNaFaktore(broj);
	}
	
	/**
	 * 
	 * @param broj (number to decompose)
	 */
	static void rastaviNaFaktore(int broj){
		
		for(int i = 2; i*i <= broj; i++) {
			
			//ako je broj djeljiv s faktorom djelimo sve dok vise nije djeljiv
			while(broj % i == 0){
				
				System.out.println(i);
				broj /= i;
			}
			
		}
		
		//ostaje prosti broj
		if (broj > 1) {
			
			System.out.println(broj);
		}
	}

}
