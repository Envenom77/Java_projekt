package hr.fer.oop.lab3.topic1.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class CdCommand extends AbstractCommand {
	
	static String commandName = "cd";
	static String commandDescription = "Navigate to given path.";

	public CdCommand() {
		super(commandName, commandDescription);
	}
	
	
	public CommandStatus execute(Environment env, String s){
		
		Path p = env.getActiveTerminal().getCurrentPath();
		System.out.println(p.toString());
		if(s.equals("..") && !(p.toString().equals("/"))) tockice(env,p);
		else {
			noviPut(env,p,s);
		}
		
		
		

		
		
		
		return CommandStatus.CONTINUE;
	}
	
	public void noviPut(Environment env, Path p, String s){
		
		File f = new File(p.toString(),s);
		
		
		if(f.exists()) {
			
			// napravi novi path
			String noviPath = p.toString() + "/" + s;
			
			//postavi novi path
			env.getActiveTerminal().setCurrentPath(Paths.get(noviPath));
		}
		else {
			f = new File(s);
			if(f.exists()){
				
				//postavi novi path
				env.getActiveTerminal().setCurrentPath(Paths.get(s));
			}
			
			//ako ne postoji zadani path
			else {
				
				System.out.println("The given path does not exist!");
				return;
				
			}
			
			
		}
		
		
		
	}
	
	
	public void tockice(Environment env, Path p){
				
		//splitaj path po "/"
		String[] put = p.toString().split("/");
		StringBuilder noviPut = new StringBuilder();
		
		//napravi novi path
		for(int i = 0; i < (put.length-1); i++) noviPut.append(put[i] + "/");
		
		String noviTrenutni = noviPut.toString();
		
		//postavi novi path
		env.getActiveTerminal().setCurrentPath(Paths.get(noviTrenutni));
		
	}
	
}
