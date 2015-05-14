package hr.fer.oop.lab1.topic2.prob1;

import hr.fer.oop.lab1.topic2.pic.Picture;
/**
 * 
 * @author josipGatjal
 *
 */
public class Ellipse implements DrawableShape{
	
	public int a,b;
	public Point p;
	//konstruktor
	/**
	 * 
	 * @param radius
	 * @param p (circle center)
	 */
	public Ellipse(int a,int b,Point p){
		this.a = a;
		this.b = b;
		this.p = p;
	}
	//konstruktor sa parametrom tipa Circle
	/**
	 * 
	 * @param c
	 */
	public Ellipse(Ellipse el){
		this(el.a, el.b, el.p);
	}
	
	//crtanje kruga
	/**
	 * 
	 * @param pic
	 */
	public void drawOnPicture(Picture pic){
		int izlaz = 0;
		for (int j = p.y - a; j <= p.y + a ; j++){
			for (int i = p.x - b; i <= p.x + b ; i++){
				if(( (b*b)*(i - p.x)*(i - p.x) + (a*a)*(j - p.y)*(j - p.y) ) <= a*a*b*b    ){
					try {
						pic.turnPixelOn(i, j);
					}
					catch ( IllegalArgumentException c){
						System.out.println("Ellipsa izvan dosega slike!");
						izlaz = 1;
					}
					
					
				}
			}
				
			}
		
		
		
		
	}

}