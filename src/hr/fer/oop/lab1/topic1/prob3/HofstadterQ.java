package hr.fer.oop.lab1.topic1.prob3;

/**
 * 
 * @author josipGatjal
 *
 */
public class HofstadterQ {
	
	//izracun niza
	/**
	 * 
	 * @param i (which number in the sequence needs to be calculated)
	 * @return long (next number of sequence)
	 */
	static long hofSequence(long i){
		
		
		if (i == 1) return 1;
		if (i == 2) return 1;
		
		
		return hofSequence(i - hofSequence(i - 1))
				+ hofSequence(i - hofSequence(i - 2));
		
		
	}
	
	/**
	 * 
	 * @param args (arguments)
	 */
	public static void main(String[] args){
		
		//provjera argumenta
		if (args.length != 1 || Integer.parseInt(args[0]) < 0){
			
			System.out.println("Error! Please give one positive number as an argument.\nExiting...");
			System.exit(0);
		}
		
		long hofBroj = hofSequence(Long.parseLong((args[0])));
		
		//ispis
		System.out.println("The " + Integer.parseInt(args[0]) + ". number of Hofstadter's Q-sequence is: " + hofBroj);
	}

}
