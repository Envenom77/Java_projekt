package hr.fer.oop.lab1.topic2.prob1;

import hr.fer.oop.lab1.topic2.pic.Picture;
/**
 * 
 * @author josipGatjal
 *
 */
public class Line implements DrawableShape{
	
	public float k, l;
	public Point p1,p2;
	
	//konstruktor
	/**
	 * 
	 * @param k (slope)
	 * @param l (y - intercept)
	 * @param p1 (first point)
	 * @param p2 (second point)
	 */
	public Line(float k, float l, Point p1, Point p2){
		this.k = k;
		this.l = l;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	//konstruktor koji prima drugu liniju kao argument
	/**
	 * 
	 * @param line
	 */
	public Line(Line line){
		this(line.k, line.l, line.p1, line.p2);
		
	}
	//cratnje linija
	/**
	 * 
	 * @param pic (the picture to draw on)
	 */
	public void drawOnPicture(Picture pic){
		
		boolean zamjenaX = false, zamjenaY =false;
		
		//ako je y koordinata jedne tocke veca od druge
		if (p2.y < p1.y){
			
			zamjeniTockeY(p1, p2);
			zamjenaY = true;
		}
		
		if (p2.x < p1.x){
			
			zamjeniTockeX(p1, p2);
			zamjenaX = true;
		
			
		}
	
		for(int j = p1.y; j <= p2.y; j++){
			
			//ako je x koordinata jedne tocke vec od druge

				
			for(int i = p1.x; i <= p2.x; i++){
				if ((j - k * i - l) == 0){
					pic.turnPixelOn(i, j);
				
				}
			}
		}
		
		//vrati tocke na prvobitno stanje (ako treba)
		if (zamjenaX){
			zamjeniTockeX(p1, p2);
		}
		
		if (zamjenaY){
			zamjeniTockeY(p1, p2);
		}
		
		
		
	}
	
	static void zamjeniTockeX(Point p1, Point p2){
		int tmp = p1.x;
		p1.x = p2.x;
		p2.x = tmp;
	}
	
	static void zamjeniTockeY(Point p1, Point p2){
		int tmp = p1.y;
		p1.y = p2.y;
		p2.y = tmp;
	}

}
