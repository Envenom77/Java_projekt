package hr.fer.oop.lab1.topic2.prob1;

import hr.fer.oop.lab1.topic2.pic.Picture;
/**
 * 
 * @author josipGatjal
 *
 */
public class Circle implements DrawableShape{
	
	public int radius;
	public Point p;
	//konstruktor
	/**
	 * 
	 * @param radius
	 * @param p (circle center)
	 */
	public Circle(int radius,Point p){
		this.radius = radius;
		this.p = p;
	}
	//konstruktor sa parametrom tipa Circle
	/**
	 * 
	 * @param c
	 */
	public Circle(Circle c){
		this(c.radius,c.p);
	}
	
	//crtanje kruga
	/**
	 * 
	 * @param pic
	 */
	public void drawOnPicture(Picture pic){
		int izlaz = 0;
		for (int j = p.y-radius; j <= p.y + radius && izlaz == 0; j++){
			for (int i = p.x - radius; i <= p.x + radius && izlaz == 0; i++){
				if(((i - p.x)*(i - p.x) + (j - p.y) * (j - p.y)) <= radius*radius){
					try {
						pic.turnPixelOn(i, j);
					}
					catch ( IllegalArgumentException c){
						System.out.println("Krug izvan dosega slike!");
						izlaz = 1;
					}
					
					
				}
			}
				
			}
		
		
		
		
	}

}
