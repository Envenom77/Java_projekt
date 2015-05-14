package hr.fer.oop.lab2.topic2.calculator;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class DigitButton extends Button{

	/**
	 * 
	 * @param buttonName, sets the button name
	 */
	public DigitButton(int buttonName) {
		super(Integer.toString(buttonName));
	}

}
