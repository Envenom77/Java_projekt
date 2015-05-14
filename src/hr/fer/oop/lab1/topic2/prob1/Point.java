package hr.fer.oop.lab1.topic2.prob1;

/**
 * 
 * @author josipGatjal
 *
 */
public class Point {
	
	public int x,y;
	
	//konstruktor
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	//konstruktor, arg tipa Point
	/**
	 * 
	 * @param p
	 */
	public Point(Point p){
		this(p.x, p.y);
	}

}
