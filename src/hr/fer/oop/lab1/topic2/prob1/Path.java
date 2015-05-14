package hr.fer.oop.lab1.topic2.prob1;

/**
 * 
 * @author josipGatjal
 *
 */
public class Path {
	
	private Point[] points;
	
	//konstruktor, prima koordinate dvije tocke + jos određeni broj tocaka za koji se pretpostavlja da je paran
	/**
	 * 
	 * @param x1 (first point x coordinate)
	 * @param y1 (first point y coordinate)
	 * @param x2 (second point x coordinate)
	 * @param y2 (second point y coordinate)
	 * @param xy (field of x/y coordinates)
	 */
	public Path(int x1, int y1, int x2, int y2, int...xy){
		//kreairanje (praznog)polja tocaka
		points = new Point[2+ xy.length/2];
		
		//kreairanje tocaka
		points[0] = new Point(x1,y1);
		points[1] = new Point(x2,y2);
		
		for(int i=0; i < xy.length/2; i++){
			points[i+2] = new Point(xy[2 *i], xy[2* i+1]);
		}
		
	}
	//vraca tocke
	/**
	 * 
	 * @return points (returns all points)
	 */
	public Point[] getPoints(){
		return points;
	}
	
	//vraca udaljenost parova tocaka
	/**
	 * 
	 * @return length (the distance between pairs of points)
	 */
	public double length() {
		double len = 0;
		for(int i=0; i < points.length - 1; i++){
			Point a = points[i];
			Point b = points[i+1];
			len += Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y -a.y) * (b.y - a.y));
		}
		
		return len;
	}
}
