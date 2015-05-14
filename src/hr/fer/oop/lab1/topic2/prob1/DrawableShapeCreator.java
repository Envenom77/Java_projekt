package hr.fer.oop.lab1.topic2.prob1;



public interface DrawableShapeCreator {
	
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
	
	DrawableShape[] create(int num);
	DrawableShape create(Shape e);
	DrawableShape createe(hr.fer.oop.lab1.topic2.prob1.RandomShapeFactory.Shape ellipse);


}
