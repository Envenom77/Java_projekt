package hr.fer.oop.lab2.topic2.calculator;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class Register {
	
	String value;
	
	/**
	 * constructor
	 */
	public Register(){
		
		this.value = "";
		
	}
	
	/**
	 * 
	 * @return , returns the value of the register
	 */
	public String getValue(){
		
		return this.value;

	}
	
	/**
	 * 
	 * @param value , sets the current value of the register to the given value
	 */
	public void setValue(String value){
		
		this.value = value;
	}
	
	/**
	 * clear the current value of the register
	 */
	public void clear(){
		
		this.value =  "";	
	}
	
	/**
	 * returns formated string
	 */
	public String toString(){
		
		return super.toString() + "(" + this.value + ")";
	}
}
