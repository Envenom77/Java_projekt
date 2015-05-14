package hr.fer.oop.lab2.topic2.calculator;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class Button {
	
	
	String buttonName;
	
	/**
	 * 
	 * @param buttonName , constructor, sets the name of the button
	 */
	public Button(String buttonName){
		this.buttonName = buttonName;
	}
	
	
	public String toString(){
		
		return super.toString() + buttonName;
	}
	
	
}
