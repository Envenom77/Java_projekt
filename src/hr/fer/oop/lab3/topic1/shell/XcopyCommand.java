package hr.fer.oop.lab3.topic1.shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class XcopyCommand extends AbstractCommand{
	
	static String commandName = "xcopy";
	static String commandDescription = "Copies a whole directory to another directory";

	
	public XcopyCommand() {
		super(commandName, commandDescription);
	}
	
	
	public CommandStatus execute(Environment env, String s){
		Path p = env.getActiveTerminal().getCurrentPath();
		String put = p.toString();
		
		int flag = 0;
		String[] putevi = s.split(" ");
		
		File src = new File(put,putevi[0]);
		File dst = new File(put,putevi[1]);
		
		
		
		if (!src.exists()){
			src = new File(putevi[0]);
			if (src.exists()){
				if (!src.isDirectory()){
					System.out.println("Source is not a directory!");
					flag = 1;
				}
			}
			//ako ne postoji
			else{
				System.out.println("Source does not exist");
				flag = 1;
			}
		}
		
		if(!src.isDirectory()) {
			System.out.println("Soruce is not a direcotry");
			flag = 1;
		}
		
		
		
		if (!dst.exists()){
			dst = new File(putevi[1]);
			if (dst.exists()){
				if (!dst.isDirectory()){
					dst = dst.getParentFile();
					if(!dst.isDirectory()) {
						System.out.println("Dest is not a directory");
						flag = 1;
					}
				}
			}
			
		}
		
		if (!dst.isDirectory()){
			dst = dst.getParentFile();
			if(!dst.isDirectory()){
				System.out.println("Dest is not a directory");
				flag = 1;
			}
		}
		
		
		
		if (flag == 0){
			
			kopiraj(src,dst);
			
		}
		
		
		
		
		return CommandStatus.CONTINUE;
	}
	
	public static void kopiraj(File src, File dst){
		
		if(src.isDirectory()){
			
			if (!dst.exists()){
				dst.mkdir();
				System.out.println("Created directory " + src + " in " + dst);
			}
			
			String[] files = src.list();
			
			for(String file: files){
				File src2 = new File(src, file);
				File dst2 = new File(dst, file);
				
				//rekurzivno kopiraj
				kopiraj(src2,dst2);
				
			}
		}
		
		else {
			
			InputStream in = null;
			try {
				in = new FileInputStream(src);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream out = null;
			try {
				out = new FileOutputStream(dst);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] buffer = new byte[1024];
			
			int length;
			
			try {
				while ((length = in.read(buffer)) > 0){
					 
					out.write(buffer, 0, length);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("File copied from " + src + " to " + dst);
 
		}
	}
}
