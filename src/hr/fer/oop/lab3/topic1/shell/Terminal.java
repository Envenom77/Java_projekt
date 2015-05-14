package hr.fer.oop.lab3.topic1.shell;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class Terminal {
	
	private int id;
	private Path currentPath = Paths.get("").toAbsolutePath();
	
	public Terminal(int id){
		
		this.id = id;
	}
	
	public int getId(){
		
		return this.id;
	}
	
	public Path getCurrentPath(){
		
		return this.currentPath;
	}
	
	
	public void setCurrentPath(Path path){
		
		this.currentPath = path;
	}
	
}
