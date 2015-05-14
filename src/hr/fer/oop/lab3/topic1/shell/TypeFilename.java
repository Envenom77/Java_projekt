package hr.fer.oop.lab3.topic1.shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class TypeFilename extends AbstractCommand {

	
	static String commandName = "type";
	static String commandDescription = "Prints content of a file";

	
	public TypeFilename() {
		super(commandName, commandDescription);
	}
	
	public CommandStatus execute(Environment env, String s){
		Path p = env.getActiveTerminal().getCurrentPath();
		String put = p.toString();
		
		File file = new File(s);
		
		if (!file.exists()){
			file = new File(put,s);
			if (!file.exists()){
				System.out.println("given path does not exist");
			}
		
		if (!file.isFile()){
			System.out.println("Given path is not a file!");
			return CommandStatus.CONTINUE;
		}
				
		}
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		int broj;
		
		try {
			while((broj = fis.read()) != -1){
				System.out.print((char)broj);
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		System.out.flush();
		
		return CommandStatus.CONTINUE;
	}


}
