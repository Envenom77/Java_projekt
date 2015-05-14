package hr.fer.oop.lab3.topic1;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class tableEntry {
	Object key = 0;
	Object value = 0;
	tableEntry next;
	
	/**
	 * 
	 * @param key, key of the element that is being created
	 * @param value, value of the element that is being created
	 * @param next, the upcoming element in the list
	 */
	public tableEntry(Object key, Object value, tableEntry next){
		this.key = key;
		this.value = value;
		this.next = next;
		
	}
	
	/**
	 * 
	 * @return , returns the key of the element
	 */
	public Object getKey(){
		
		return this.key;
		
	}
	
	/**
	 * 
	 * @return, returns the value of the element
	 */
	public Object getValue(){
		
		return this.value;
	}
	
	/**
	 * 
	 * @param value , the value we want to set the element to
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * returns formated string
	 */
	public String toString(){
		
		return super.toString() + "(" + key + "," + value + ")";
		
	}

}
