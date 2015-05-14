package hr.fer.oop.lab1.topic2.prob1;

import hr.fer.oop.lab1.topic2.pic.Picture;
import hr.fer.oop.lab1.topic2.pic.PictureDisplay;
import hr.fer.oop.lab1.topic2.prob1.DrawableShapeCreator.Shape;

import java.util.Random;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class RandomShapeFactory implements DrawableShapeCreator {
	
	
    int maxWidth = 0;
	int maxHeight = 0;
	
	static int height = 500;
	static int width = 500;
	
	/**
	 * 
	 * @param maxWidth , max width of the shapes.
	 * @param maxHeight, max height of the shapes.
	 */
	public RandomShapeFactory(int maxWidth, int maxHeight) {
		
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}
	
	public enum Shape{
		circle("circle"), rectangle("rectangle"), line("line"), ellipse("ellipse");
		
		private String value;
		
		private Shape(String value){
			this.value = value;
		}
		
		public String getValue(){
			
			return this.value;
		}
		
	}
	

	
	@Override
	/**
	 * function that creates the given number num of shapes and puts them into the field shapes and returns the field
	 */
	public DrawableShape[] create(int num) {
		// napravi novo polje
		DrawableShape[] shapes = new DrawableShape[num];
		
		//stvori nove random DrawableShape i stavi u polje
		for(int i = 0; i < num; i++){
			int random = randShapeType();
			
			if(random == 1){
				Circle c = stvoriKrug();
				shapes[i] = c;
			}
			
			if(random == 2){
				Rectangle r = stvoriPravokutnik();
				shapes[i] = r;
			}
			
			if(random == 3){
				Line l = stvoriLajnu();
				shapes[i] = l;
			}
		}
		
		
		return shapes;
	}


	@Override
	//ovisno o stringu stvori neki DrawableShape
	/**
	 * function that creates a single shape depending on the given string
	 */
	public DrawableShape create(hr.fer.oop.lab1.topic2.prob1.DrawableShapeCreator.Shape val) {
		
		if (val.getValue().equals("circle")){
			
			Circle c = stvoriKrug();
			return c;
			
		}
		
		if (val.getValue().equals("line")){
		
			Line l = stvoriLajnu();
			
			return l;
			
		}
		
		if (val.getValue().equals("rectangle")){
			
			Rectangle r = stvoriPravokutnik();
			return r;
		}
		
		
		return null;
	}
	
	//random broj od [1,3] za odabir oblika
	/**
	 * 
	 * @return , returns a number in the interval [1,3], used to choose which type of shape to create.
	 */
	public static int randShapeType(){
		Random rand = new Random();
		
		int randNum = rand.nextInt((3-1)+1)+1;
		
		return randNum;
	}
	
	//stvaranje random tocke sa koordinatama x,y
	/**
	 * 
	 * @return , creates and returns a point with random coordinates x,y interval [0,maxwidth-1]
	 */
	public static Point randPoint(){
		Random rand = new Random();
		
		Point randPoint = new Point(rand.nextInt((((height - 1) - 0 ) + 1) + 0), rand.nextInt((((width - 1) - 0 ) + 1) + 0));
		
		return randPoint;
	}
	
	//random int za radijus kruga [1,maxwidht/4]
	/**
	 * 
	 * @return, returns an int value in the interval [0,maxwidth/4-1]
	 */
	public static int randRadijus(){
		Random rand = new Random();
		
		int randRadius = rand.nextInt((width/4) - 1);
		
		return randRadius;
	}
	
	//random int od [0,maxheight/2-1]
	/**
	 * 
	 * @return , returns an int value in the interval [0,maxwidth/2-1]
	 */
	public static int randSize(){
		Random rand = new Random();
		
		int randSize = rand.nextInt(((width/2-1)-0)+ 1) + 0;
		
		return randSize;
	}
	
	//funkcija za stvaranje kruga, vraca stvoreni krug
	/**
	 * 
	 * @return, returns a random DrawableShape type Circle
	 */
	public static Circle stvoriKrug(){
		
		Point p = randPoint();
		int radijus = randRadijus();
		
		p.x += radijus;
		p.y += radijus;
		
		Circle c = new Circle(radijus,p);
		System.out.println("stvoren krug");
		
		return c;
	}
	
	//funkcija za stvaranje pravokutnika, vraca stvoreni pravokutnik
	/**
	 * 
	 * @return, returns a random DrawableShape type Circle
	 */
	public static Rectangle stvoriPravokutnik(){
		
		Point p = randPoint();
		int height = randSize();
		int width = randSize();
		
		Rectangle r = new Rectangle(height, width, p);
		System.out.println("stvoren pravokutnik");
		return r;
	}
	
	//funkcija za stvaranje linije, vraca stvorenu liniju
	/**
	 * 
	 * @return, returns a random DrawableShape type Circle
	 */
	public static Line stvoriLajnu(){
		
		Point p1 = randPoint();
		Point p2 = randPoint();
		
		int k = (p2.y-p1.y)/(p2.x-p1.x);
		int l = -k * p1.x + p1.y;
		
		Line l1 = new Line(k, l, p1, p2);
		System.out.println("stvorena linija sa parametrima: " + k +" "+ l + " " + p1.x +" " + p1.y + " " + p2.x + " " + p2.y);
		return l1;
		
	}
	
	public static Ellipse stvoriElipsu(){
		
		Point p = new Point(200,200);
		
		int a = 100;
		int b = 80;
		
		
		Ellipse el = new Ellipse(a,b,p);
		System.out.println("stvorena elipsa sa param " + a + " " + b + " " + p.x + " " + p.y);
		
		return el;
		
		
	}
	

	/**
	 * 
	 * @param args , arguments for the main function, none needed.
	 */
	public static void main(String[] args) {
		
		//stvori novu "tvornicu"
		DrawableShapeCreator factory = new RandomShapeFactory(height, width);
		
		//napuni polje sa oblicima
		DrawableShape[] shapes = factory.create(5);
		
		DrawableShape elipsa = factory.createe(Shape.ellipse);
		
	
		
		//stvori novu sliku
		Picture pic = new Picture(height, width);
		
		//nacrtaj oblike na slici
//		for (DrawableShape s :shapes){
//			try{
//				s.drawOnPicture(pic);
//			}
//			catch(NullPointerException r){
//				System.out.println("Zadani oblik je null!");
//				System.exit(1);
//			}
//		}
		
		elipsa.drawOnPicture(pic);
		
		
		
		//prikazi sliku
		PictureDisplay.showPicture(pic);
	
	}

	@Override
	public DrawableShape createe(Shape ellipse) {
		
		Ellipse el = stvoriElipsu();
		return el;
	}






	



}
