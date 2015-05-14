package hr.fer.oop.lab1.topic2.prob1;

import hr.fer.oop.lab1.topic2.pic.Picture;

/**
 * 
 * @author josipGatjal
 *
 */
public class Rectangle implements DrawableShape{
	
	public int height,width;
	public Point p;
	//konstruktor
	/**
	 * 
	 * @param height
	 * @param width
	 * @param p (starting point, lower-left
	 */
	public Rectangle(int height,int width,Point p){
		
		this.height = height;
		this.width = width;
		this.p = p;
	}
	//konstruktor s argumentom tipa Reactangle
	/**
	 * 
	 * @param r
	 */
	public Rectangle(Rectangle r){
		this(r.height, r.width, r.p);
	}
	//crtanje pravokutnika
	/**
	 * 
	 * @param pic
	 */
	public void drawOnPicture(Picture pic){
	

	{	
		int izlaz = 0;
		for(int j = p.y; j < height + p.y && izlaz == 0; j++)
			for(int i = p.x ; i < width + p.x && izlaz == 0; i++){
				try {
					pic.turnPixelOn(i, j);
				}
				catch(IllegalArgumentException c){
					System.out.println("Pravokutnik izvan doesga slike!");
					izlaz = 1;
				}
			}
		
	}
	
		
		
	}
	

}
