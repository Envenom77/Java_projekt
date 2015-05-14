package hr.fer.oop.lab3.topic1.shell;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class AbstractCommand implements ShellCommand{
	
	private String commandName;
	private String commandDescription;
	
	
	public AbstractCommand(String commandName, String commandDescription) {
		this.commandName = commandName;
		this.commandDescription = commandDescription;
	}

	@Override
	public String getCommandName() {
		
		return this.commandName;
	}
	
	@Override
	public String getCommandDescription() {
		return this.commandDescription;
	}
	
	
	@Override
	public CommandStatus execute(Environment env, String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
