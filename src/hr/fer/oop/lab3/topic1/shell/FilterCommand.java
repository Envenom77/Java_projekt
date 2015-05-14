package hr.fer.oop.lab3.topic1.shell;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class FilterCommand extends AbstractCommand {

	public FilterCommand() {
		super("filter", "Prints files in current and subdirectories that fit the filter");
		}

	public class Filter extends SimpleFileVisitor<Path>{
	
		String prefix = "", sufix = "";
		
		public Filter(String prefix, String sufix) {
			this.prefix = prefix;
			this.sufix = sufix;
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
		
			String prefix = file.toFile().getName().substring(0, this.prefix.length());
			String sufix = file.toFile().getName().substring(file.toFile().getName().length()-this.sufix.length());
			
			if (prefix.equalsIgnoreCase(this.prefix) && sufix.equalsIgnoreCase(this.sufix)){
				System.out.println(file.toFile().getAbsolutePath());
			}
			return FileVisitResult.CONTINUE;
		}
	
	}
	
	public CommandStatus execute (Environment env, String arg){
	
		Path startingDir = env.getActiveTerminal().getCurrentPath();
		System.out.println(startingDir.toString());
		String[] sliceName = arg.split("\\*");
		String tmp = "*";
		Filter filterFile;
		
		if (sliceName.length == 2){ //it means that both prefix and the suffix, or just a suffix
			filterFile = new Filter(sliceName[0],sliceName[1]);
		
		} else if (arg.substring(arg.length()-1).equals(tmp)) {//it means we have a prefix
			filterFile = new Filter(sliceName[0],"");
			
		} else {//it is invalid argumnet
			env.writeln("Argumnet is invalid please try again.");
		
		
		return CommandStatus.CONTINUE;
		
		}
		
		try {
			Files.walkFileTree(startingDir, filterFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return CommandStatus.CONTINUE;
	}
	}