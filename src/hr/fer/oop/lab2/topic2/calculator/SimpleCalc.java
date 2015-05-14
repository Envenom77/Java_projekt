package hr.fer.oop.lab2.topic2.calculator;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class SimpleCalc implements Calculator {
	
	//stvaranje potrebnih registara
	Register Display = new Register(); 
	Register Memory = new Register();
	Register Operator = new Register();
	
	@Override
	/**
	 * returns the current display
	 */
	public String getDisplay() {
		// TODO Auto-generated method stub
		if (Display.getValue() == "") return "0";
		return Display.getValue();	
	}

	@Override
	/**
	 * Decides what to do when a certain button is pressed
	 */
	public void press(Button button) {
		
		//ako je pritisnut broj
		if (button instanceof DigitButton){
			Display.setValue(Display.getValue() + button.buttonName);
			System.out.println(Display.getValue());
		}
		
		//ako je pritisnut operator
		if (button instanceof OperatorButton){
			
			//prebacujemo prvi broj sa zaslona u memoriju
			if(Memory.getValue() != ""){
				if(Operator.getValue() == "+"){
					Memory.setValue(Integer.toString(Integer.parseInt(Display.getValue()) + Integer.parseInt(Memory.getValue())));
				}
				
				else Memory.setValue(Integer.toString(Integer.parseInt(Memory.getValue()) - Integer.parseInt(Display.getValue())));
				
			}
			
			else Memory.setValue(Display.getValue());
			
			Display.clear();
			
			//postavljamo trenutni display na znak + ili -
			Operator.setValue(button.buttonName);
		}
		
		//ako je pritisnut =
		if (button instanceof mainOperatorButton){
			
			if (Memory.getValue() == ""){
				System.out.println("You didnt give 2 numbers! Exiting...");
				System.exit(0);
			}
			//ako treba obaviti zbrajanje
			if(Operator.value == "+"){
				Display.setValue(Integer.toString(Integer.parseInt(Display.getValue()) + Integer.parseInt(Memory.getValue())));
			}
			
			//ako treba obaviti oduzimanje
			if(Operator.value == "-"){
				Display.setValue(Integer.toString(Integer.parseInt(Display.getValue()) - Integer.parseInt(Memory.getValue())));
			}
			
			Memory.clear();
			Operator.clear();
			
		}
		
		
	}
	
	/**
	 * 
	 * @param broj , the number of the button/digit that is pressed
	 */
	public void pressDigit(int broj){
		
		//stvaramo novi digitbutton i saljemo ga press funkciji
		DigitButton button = new DigitButton(broj);
		System.out.println(button.buttonName);
		press(button);
	}
	
	/**
	 *  equals button is pressed
	 */
	public void pressEquals(){
		
		mainOperatorButton button = new mainOperatorButton("=");
		press(button);
		
	}
	
	/**
	 * plus button is pressed
	 */
	public void pressPlus(){
		
		//stvaramo novi OperatorButton i saljemo ga press funkciji
		OperatorButton button = new OperatorButton("+");
		press(button);
		
	}
	
	/**
	 * minus button is pressed
	 */
	public void pressMinus(){
		
		//stvaramo novi OperatorButton i saljemo ga press funkciji
		OperatorButton button = new OperatorButton("-");
		press(button);		
	
	}
	
	/**
	 * Clear button is pressed
	 */
	public void pressClear(){
		
		//brišemo sadržaje svih registara
		Memory.clear();
		Display.clear();
		Operator.clear();

	}
	
	/**
	 * returns formated string
	 */
	public String toString(){
		
		return  "Display: " + Display.getValue() + " Memory: " + Memory.getValue() + " Operator: " + Operator.getValue();
	}
	
	/**
	 * compares 2 calculators
	 * @param calc, the calculator we want to compare
	 * @return true if the calculators are the same, else false
	 */
	public boolean equals(SimpleCalc calc){
		
		//provjeri sve registre
		if(this.Operator.getValue().equals(calc.Operator.getValue())
			&& this.Display.getValue().equals(calc.Display.getValue())
			&& this.Memory.getValue().equals(calc.Memory.getValue()) )
		{
			
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * @param args , arguments given from the user
	 */
	public static void main(String[] args) {
		SimpleCalc c = new SimpleCalc();
		c.pressDigit(1);
		c.pressDigit(0);
		System.out.println(c.toString());
		c.pressMinus();
		System.out.println(c.toString());
		System.out.println(c.getDisplay());
		c.pressDigit(2);
		System.out.println(c.getDisplay());
		c.pressDigit(9);
		System.out.println(c.toString());
		c.pressPlus();
		System.out.println(c.toString());
		c.pressDigit(3);
		c.pressEquals();
		c.pressClear();
	}


}
