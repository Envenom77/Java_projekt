package hr.fer.oop.lab3.topic1.shell;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class QuitCommand extends AbstractCommand {
	
	static String commandName = "quit"; 
	static String commandDescription = "Exit shell";
	
	public QuitCommand() {
		super(commandName, commandDescription);
		
	}
	
	public CommandStatus execute(Environment env,String s){
		
		return CommandStatus.EXIT;
	}

}
